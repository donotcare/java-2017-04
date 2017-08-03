package ru.otus.l16.message;

import ru.otus.l16.message.runner.ProcessRunnerImpl;
import ru.otus.l16.message.server.SocketServer;
import ru.otus.l16.message.server.BalancerService;
import ru.otus.l16.message.server.FrontService;
import ru.otus.l16.message.server.MessageSystem;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final String START_COMMAND = "java -jar ";
    private static final int CLIENT_START_DELAY_SEC = 15;

    public static void main(String[] args) throws Exception {
        new Main().start();
    }

    private void start() throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        MessageSystem messageSystem = new MessageSystem();
        startClient(executorService, SocketClient.BACKEND1);
        startClient(executorService, SocketClient.BACKEND2);
        startClient(executorService, SocketClient.FRONTEND1);
        startClient(executorService, SocketClient.FRONTEND2);

        SocketServer server = createSocketServer(messageSystem);

        BalancerService balancerService = new BalancerService(server);
        messageSystem.addAddressee(balancerService);

        FrontService frontService = new FrontService(server);
        messageSystem.addAddressee(frontService);

        messageSystem.start();
        server.start();
        executorService.shutdown();
    }

    private SocketServer createSocketServer(MessageSystem messageSystem) throws Exception {
        return new SocketServer(messageSystem);
    }

    private void startClient(ScheduledExecutorService executorService, SocketClient client) {
        executorService.schedule(() -> {
            try {
                new ProcessRunnerImpl().start(START_COMMAND + client.getCommand());
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }, CLIENT_START_DELAY_SEC, TimeUnit.SECONDS);
    }
}
