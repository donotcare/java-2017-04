package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

public class FrontResponseMessage extends Message {
    private final String data;

    public FrontResponseMessage(String data, Address from, Address to) {
        super(FrontResponseMessage.class, from ,to);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Answer{" + "data=" + data + '}';
    }

    @Override
    public void exec(Addressee addressee) {

    }
}
