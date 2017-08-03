package ru.otus.l16.frontend.service;

import org.eclipse.jetty.websocket.api.Session;
import ru.otus.l16.message.ClientUtils;
import ru.otus.l16.message.channel.SocketClientChannel;
import ru.otus.l16.message.server.Address;
import ru.otus.l16.message.server.FrontResponseMessage;
import ru.otus.l16.message.server.MessageGetCacheStat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ClientFrontService {
    private static final Logger logger = Logger.getLogger(ClientFrontService.class.getName());
    private final SocketClientChannel client;
    private final Address address;
    private Session currentSession;

    public ClientFrontService(Address address, SocketClientChannel client) {
        this.address = address;
        this.client = client;
    }

    public void init() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (true) {
                try {
                    FrontResponseMessage receivedMsg = (FrontResponseMessage) client.take();
                    currentSession.getRemote().sendString(receivedMsg.getData());
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });
    }

    public void requestCachedValue(Session session) {
        currentSession = session;
        MessageGetCacheStat msg = new MessageGetCacheStat(address, ClientUtils.getBalancerAddress());
        client.send(msg);
    }
}
