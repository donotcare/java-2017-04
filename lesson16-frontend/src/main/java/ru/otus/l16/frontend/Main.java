package ru.otus.l16.frontend;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.l16.frontend.service.ClientFrontService;
import ru.otus.l16.frontend.web.LoginServlet;
import ru.otus.l16.frontend.web.SettingsServlet;
import ru.otus.l16.frontend.web.TemplateProccesor;
import ru.otus.l16.frontend.websocket.MessageWebSocketServlet;
import ru.otus.l16.message.ClientUtils;
import ru.otus.l16.message.channel.SocketClientChannel;
import ru.otus.l16.message.system.Address;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        Address address = ClientUtils.getAddress(args);
        int port = ClientUtils.getPort(args);
        new Main().start(address, port);
    }

    private void start(Address address, int port) throws Exception {
        logger.log(Level.SEVERE, "Started on port: " + port);
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("./");
        ResourceHandler resourceHandler = new ResourceHandler();
        SocketClientChannel client = new SocketClientChannel(new Socket(ClientUtils.getSocketHost(), ClientUtils.getSocketPort()));
        client.init();
        ClientFrontService front = new ClientFrontService(address, client);
        front.init();
        TemplateProccesor templateProccesor = new TemplateProccesor();
        templateProccesor.init();

        context.addServlet(new ServletHolder(new MessageWebSocketServlet(front)), "/message");
        context.addServlet(new ServletHolder(new LoginServlet(templateProccesor)), "/login");
        context.addServlet(new ServletHolder(new SettingsServlet(templateProccesor, port)), "/settings");
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();
    }
}
