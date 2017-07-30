package ru.otus.l10.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.l10.message.FrontService;

public class MessageWebSocketServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    @Autowired
    private FrontService frontService;

    @Override
    public void configure(WebSocketServletFactory factory) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new MessageWebSocketCreator(frontService));
    }
}
