package ru.otus.l16.frontend.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.otus.l16.frontend.service.ClientFrontService;

@WebSocket
public class MessageWebSocket {
    private Session session;
    private ClientFrontService frontService;

    public MessageWebSocket(ClientFrontService frontService) {
        this.frontService = frontService;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        if (data.equals("get_cached")) {
            frontService.requestCachedValue(session);
        }
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        setSession(session);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
