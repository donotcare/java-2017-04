package service.message;

public class MessageGetCacheStatAnswer extends Message {
    private final int cached;

    public MessageGetCacheStatAnswer(Address from, Address to, int cached) {
        super(from, to);
        this.cached = cached;
    }

    @Override
    public void exec(Addressee addressee) {

    }
}
