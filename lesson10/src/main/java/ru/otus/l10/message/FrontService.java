package ru.otus.l10.message;


import org.eclipse.jetty.websocket.api.Session;

public class FrontService implements Addressee {
    private final Address address;
    private MessageSystemContext context;

    public FrontService() {
        this.address = new Address("Frontend");
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void setContext(MessageSystemContext context) {
        this.context = context;
    }

    public void requestCachedValue(Session session) {
        context.getMessageSystem().sendMessage(new MessageGetCacheStat(address,
                context.getBackAddress(), context.getMessageSystem(), session));
    }

    public void sendCachedValueToFront(Session session, String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}
