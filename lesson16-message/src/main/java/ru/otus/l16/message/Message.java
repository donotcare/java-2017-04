package ru.otus.l16.message;

import ru.otus.l16.message.server.Address;
import ru.otus.l16.message.server.Addressee;

public abstract class Message {
    public static final String CLASS_NAME_VARIABLE = "className";

    private Address to;
    private final Address from;
    private final String className;

    protected Message(Class<?> clazz, Address from, Address to) {
        this.className = clazz.getName();
        this.to = to;
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public Address getFrom() {
        return from;
    }
    
    public abstract void exec(Addressee addressee);
}
