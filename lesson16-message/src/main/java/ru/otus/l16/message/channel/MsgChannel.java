package ru.otus.l16.message.channel;

import ru.otus.l16.message.Message;

import java.io.IOException;

public interface MsgChannel {
    void send(Message msg);

    Message pool();

    Message take() throws InterruptedException;

    void close() throws IOException;
}
