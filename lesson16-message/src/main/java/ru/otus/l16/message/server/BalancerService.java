package ru.otus.l16.message.server;

import ru.otus.l16.message.ClientUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BalancerService implements Addressee {
    private static final Logger logger = Logger.getLogger(BalancerService.class.getName());

    private final SocketServer socketServer;
    private final List<Address> backendAdresses = new ArrayList<>();
    private final Random random = new Random();

    public BalancerService(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    public void getCacheStat(Address from) {
        logger.info("getCacheStat" + " from " + from);
        while(backendAdresses.size() == 0) {
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e) {
                logger.info("Error");
            }
        }
        Address backendAddress = backendAdresses.get(random.nextInt(backendAdresses.size()));
        logger.info("Backend wins: " + backendAddress);
        socketServer.sendMessage(backendAddress, new FrontRequestMessage(from, backendAddress));
    }

    public void addBackendAddress(Address address) {
        logger.info("Add back address");
        backendAdresses.add(address);
    }

    @Override
    public Address getAddress() {
        return ClientUtils.getBalancerAddress();
    }
}
