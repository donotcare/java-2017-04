package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

public class FrontRequestMessage extends Message {

    public FrontRequestMessage(Address from, Address to) {
        super(FrontRequestMessage.class, from , to);
    }

    @Override
    public String toString() {
        return "Get cache{" + "time=" + '}';
    }

    @Override
    public void exec(Addressee addressee) {

    }
}
