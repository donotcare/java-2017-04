package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

public class PingMessage extends Message {

    public PingMessage(Address from, Address to) {
        super(PingMessage.class, from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        ((BalancerService)addressee).addBackendAddress(getFrom());
    }
}
