package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

public class PingMessage extends Message {
    private final long time;

    public PingMessage(Address from, Address to) {
        super(PingMessage.class, from, to);
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "PingMsg{" + "time=" + time + '}';
    }

    @Override
    public void exec(Addressee addressee) {
        ((BalancerService)addressee).addBackendAddress(getFrom());
    }
}
