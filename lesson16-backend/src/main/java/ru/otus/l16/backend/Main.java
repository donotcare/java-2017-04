package ru.otus.l16.backend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.l16.backend.service.MessageHandler;
import ru.otus.l16.message.ClientUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        MessageHandler handler = ctx.getBean(MessageHandler.class);
        handler.setAddress(ClientUtils.getAddress(args));
        handler.start();
    }

}
