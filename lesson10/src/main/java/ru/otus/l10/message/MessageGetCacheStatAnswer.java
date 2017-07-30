package ru.otus.l10.message;

import org.eclipse.jetty.websocket.api.Session;

public class MessageGetCacheStatAnswer extends Message {
    private final String cacheData;
    private final Session session;

    public MessageGetCacheStatAnswer(Address from, Address to, String cacheData, Session session) {
        super(from, to);
        this.cacheData = cacheData;
        this.session = session;
    }

    @Override
    public void exec(Addressee addressee) {
        ((FrontService) addressee).sendCachedValueToFront(session, cacheData);
    }
}
