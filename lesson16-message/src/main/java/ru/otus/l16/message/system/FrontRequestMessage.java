package ru.otus.l16.message.system;

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
