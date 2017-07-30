package ru.otus.l10.message;

import ru.otus.l10.cache.CacheEngine;
import org.eclipse.jetty.websocket.api.Session;

public class MessageGetCacheStat extends Message {
    private final MessageSystem messageSystem;
    private final Session session;

    public MessageGetCacheStat(Address from, Address to, MessageSystem messageSystem, Session session) {
        super(from, to);
        this.messageSystem = messageSystem;
        this.session = session;
    }

    @Override
    public void exec(Addressee addressee) {
        CacheEngine cache = (CacheEngine) addressee;
        String data = String.format("{\"hit\":%s, \"miss\":%s, \"cached\":%s}", cache.getHitCount(), cache.getMissCount(), cache.getCachedElementsCount());
        messageSystem.sendMessage(new MessageGetCacheStatAnswer(getTo(), getFrom(), data, session));
    }
}
