package service.message;

import cache.CacheEngine;

public class MessageGetCacheStat extends Message {
    private final MessageSystem messageSystem;

    public MessageGetCacheStat(Address from, Address to, MessageSystem messageSystem) {
        super(from, to);
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(Addressee addressee) {
        int cached = ((CacheEngine) addressee).getCachedElementsCount();
        messageSystem.putMessage(new MessageGetCacheStatAnswer(getTo(), getFrom(), cached));
    }
}
