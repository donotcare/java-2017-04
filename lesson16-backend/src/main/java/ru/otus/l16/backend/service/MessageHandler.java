package ru.otus.l16.backend.service;

import ru.otus.l16.backend.Main;
import ru.otus.l16.backend.cache.CacheEngine;
import ru.otus.l16.message.ClientUtils;
import ru.otus.l16.message.system.PingMessage;
import ru.otus.l16.message.channel.SocketClientChannel;
import ru.otus.l16.message.system.Address;
import ru.otus.l16.message.system.Message;
import ru.otus.l16.message.system.MessageGetCacheStatAnswer;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageHandler {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final int PAUSE_MS = 5000;
    private static final String HOST = "localhost";
    private static final int SOCKET_PORT = 5050;

    private Address address;
    private SocketClientChannel client;
    private CacheEngine cache;

    public MessageHandler(CacheEngine cache) {
        this.cache = cache;
    }

    public void start() throws IOException {
        client = new SocketClientChannel(new Socket(HOST, SOCKET_PORT));
        client.init();
        client.send(new PingMessage(address, new Address("Balancer")));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Message receivedMsg = client.take();
                    String data = String.format("{\"hit\":%s, \"miss\":%s, \"cached\":%s}", cache.getHitCount(), cache.getMissCount(), cache.getCachedElementsCount());
                    Message msg = new MessageGetCacheStatAnswer(address, ClientUtils.getFrontServiceAddress(), receivedMsg.getFrom(), data);
                    client.send(msg);
                    Thread.sleep(PAUSE_MS);
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        });
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
