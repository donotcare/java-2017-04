package ru.otus.l16.message.server;

import ru.otus.l16.message.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 1000;

    private final Map<Address, AddresseeMessages> addresseeMap = new HashMap<>();

    public void addAddressee(Addressee addressee) {
        addresseeMap.put(addressee.getAddress(), new AddresseeMessages(addressee));
    }

    public void sendMessage(Message message) {
        addresseeMap.get(message.getTo()).messageQueue.add(message);
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new MessageProcessTask(), DEFAULT_STEP_TIME, DEFAULT_STEP_TIME);
    }

    private class MessageProcessTask extends TimerTask {
        @Override
        public void run() {
            addresseeMap.forEach(this::processAddressAsync);
        }

        private void processAddressAsync(Address address, AddresseeMessages addresseeMessages) {
            CompletableFuture.runAsync(() -> {
                ConcurrentLinkedQueue<Message> queue = addresseeMessages.messageQueue;
                while (!queue.isEmpty()) {
                    Message message = queue.poll();
                    message.exec(addresseeMessages.addressee);
                }
            });
        }
    }

    private static class AddresseeMessages {
        public final Addressee addressee;
        public final ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();

        private AddresseeMessages(Addressee addressee) {
            this.addressee = addressee;
        }
    }
}
