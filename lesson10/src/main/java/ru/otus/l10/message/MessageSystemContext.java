package ru.otus.l10.message;

public class MessageSystemContext {
    private final MessageSystem messageSystem;

    private final Address frontAddress;
    private final Address backAddress;

    public MessageSystemContext(MessageSystem messageSystem, Address backend, Address frontend) {
        this.messageSystem = messageSystem;
        this.backAddress = backend;
        this.frontAddress = frontend;

    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFrontAddress() {
        return frontAddress;
    }

    public Address getBackAddress() {
        return backAddress;
    }

}
