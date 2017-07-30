package ru.otus.l10.websocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.otus.l10.message.FrontService;

public class MessageWebSocketCreator implements WebSocketCreator {
    private final FrontService frontService;

    public MessageWebSocketCreator(FrontService frontService) {
        this.frontService = frontService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        return new MessageWebSocket(frontService);
    }
}
