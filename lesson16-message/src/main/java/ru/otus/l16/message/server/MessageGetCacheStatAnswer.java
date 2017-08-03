package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

public class MessageGetCacheStatAnswer extends Message {
    private final String cacheData;
    private final Address frontAddress;

    public MessageGetCacheStatAnswer(Address from, Address to, Address frontAdress, String cacheData) {
        super(MessageGetCacheStatAnswer.class, from, to);
        this.cacheData = cacheData;
        this.frontAddress = frontAdress;
    }

    @Override
    public void exec(Addressee addressee) {
        ((FrontService) addressee).receiveCacheStat(frontAddress, cacheData);
    }
}
