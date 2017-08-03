package ru.otus.l16.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.l16.backend.cache.CacheEngine;
import ru.otus.l16.backend.cache.CacheSettings;
import ru.otus.l16.backend.model.UserDataSet;
import ru.otus.l16.backend.service.MessageHandler;
import ru.otus.l16.backend.service.RandomGetter;
import ru.otus.l16.backend.service.UsersCachedService;
import ru.otus.l16.backend.service.UsersService;


@Configuration
public class SpringConfig {

    @Bean
    public UsersCachedService usersCachedService() {
        return new UsersCachedService(new UsersService(), cacheEngine());
    }

    @Bean
    public CacheEngine<Long, UserDataSet> cacheEngine() {
        return new CacheEngine<>(new CacheSettings(10, 0, 0, false));
    }

    @Bean
    public RandomGetter randomGetter() {
        return new RandomGetter(usersCachedService());
    }

    @Bean
    public MessageHandler messageReceiver() {
        return new MessageHandler(cacheEngine());
    }
}
