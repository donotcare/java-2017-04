package ru.otus.l16.frontend.websocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.otus.l16.frontend.service.ClientFrontService;

public class MessageWebSocketCreator implements WebSocketCreator {
    private final ClientFrontService frontService;

    public MessageWebSocketCreator(ClientFrontService frontService) {
        this.frontService = frontService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        return new MessageWebSocket(frontService);
    }
}
