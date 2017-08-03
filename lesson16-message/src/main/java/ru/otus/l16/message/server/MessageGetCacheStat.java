package ru.otus.l16.message.server;


import ru.otus.l16.message.Message;

public class MessageGetCacheStat extends Message {
    private int id;

    public MessageGetCacheStat(Address from, Address to) {
        super(MessageGetCacheStat.class, from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        BalancerService balancerService = (BalancerService) addressee;
        balancerService.getCacheStat(getFrom());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
