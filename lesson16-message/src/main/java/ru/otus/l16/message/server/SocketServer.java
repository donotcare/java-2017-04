package ru.otus.l16.message.server;

import ru.otus.l16.message.ClientUtils;
import ru.otus.l16.message.Message;
import ru.otus.l16.message.channel.MsgChannel;
import ru.otus.l16.message.channel.SocketClientChannel;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class SocketServer {
    private static final Logger logger = Logger.getLogger(SocketServer.class.getName());

    private static final int THREADS_NUMBER = 1;
    private static final int PORT = 5050;
    private static final int MIRROR_DELAY = 100;

    private final ExecutorService executor;
    private final Map<Address, MsgChannel> channelAddresses;
    private final List<MsgChannel> channels;

    private final MessageSystem messageSystem;

    public SocketServer(MessageSystem messageSystem) {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        channels = new ArrayList<>();
        channelAddresses = new HashMap<>();
        this.messageSystem = messageSystem;
    }

    public void start() throws Exception {
        executor.submit(this::getMessage);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket client = serverSocket.accept();
                logger.info("New client");
                SocketClientChannel channel = new SocketClientChannel(client);
                channel.addShutdownRegistration(() -> closedChannelRemove(channel));
                channel.init();
                channels.add(channel);

            }
        }
    }

    private void closedChannelRemove(MsgChannel channel) {
        channels.remove(channel);
        List<Address> addressesToRemove = channelAddresses.entrySet().stream()
                .filter(e -> e.getValue() == channel)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        addressesToRemove.forEach(channelAddresses::remove);
        Address balancerAddress = ClientUtils.getBalancerAddress();
        messageSystem.sendMessage(new RemoveBackendAddressesMessage(addressesToRemove, balancerAddress));
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void getMessage() {
        while (true) {
            for (MsgChannel channel : channels) {
                Message msg = channel.pool();
                if (msg != null) {
                    logger.info("From: " + msg.getFrom());
                    channelAddresses.putIfAbsent(msg.getFrom(), channel);
                    messageSystem.sendMessage(msg);
                }
            }
            try {
                Thread.sleep(MIRROR_DELAY);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
    }

    public void sendMessage(Address address, Message msg) {
        channelAddresses.get(address).send(msg);
    }

}
