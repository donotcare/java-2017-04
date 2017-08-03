package ru.otus.l16.message.system;

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
