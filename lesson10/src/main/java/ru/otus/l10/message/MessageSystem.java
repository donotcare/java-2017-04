package ru.otus.l10.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 1000;

    private final Map<Address, ConcurrentLinkedQueue<Message>> messagesMap = new ConcurrentHashMap<>();
    private final Map<Address, Addressee> addresseeMap = new HashMap<>();

    public void addAddressee(Addressee addressee) {
        addresseeMap.put(addressee.getAddress(), addressee);
        messagesMap.put(addressee.getAddress(), new ConcurrentLinkedQueue<>());
    }

    public void sendMessage(Message message) {
        messagesMap.get(message.getTo()).add(message);
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

        private void processAddressAsync(Address address, Addressee addressee) {
            CompletableFuture.runAsync(() -> {
                ConcurrentLinkedQueue<Message> queue = messagesMap.get(address);
                while (!queue.isEmpty()) {
                    Message message = queue.poll();
                    message.exec(addressee);
                }
            });
        }
    }
}
