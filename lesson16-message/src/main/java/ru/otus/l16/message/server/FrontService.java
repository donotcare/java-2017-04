package ru.otus.l16.message.server;

import ru.otus.l16.message.ClientUtils;

import java.util.logging.Logger;

public class FrontService implements Addressee {
    private static final Logger logger = Logger.getLogger(BalancerService.class.getName());
    private final SocketServer socketServer;

    public FrontService(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    public void receiveCacheStat(Address to, String data) {
        logger.info("send data to:" + to + " " + data);
        socketServer.sendMessage(to, new FrontResponseMessage(data, getAddress(), to));
    }

    @Override
    public Address getAddress() {
        return ClientUtils.getFrontServiceAddress();
    }
}
