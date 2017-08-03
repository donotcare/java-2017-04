package ru.otus.l16.frontend.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import ru.otus.l16.frontend.service.ClientFrontService;

public class MessageWebSocketServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;

    private final ClientFrontService frontService;

    public MessageWebSocketServlet(ClientFrontService frontService) {
        this.frontService = frontService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new MessageWebSocketCreator(frontService));
    }
}
