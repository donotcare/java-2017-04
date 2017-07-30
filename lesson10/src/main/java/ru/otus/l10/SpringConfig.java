package ru.otus.l10;

import ru.otus.l10.cache.CacheEngine;
import ru.otus.l10.cache.CacheSettings;
import ru.otus.l10.model.UserDataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.l10.service.RandomGetter;
import ru.otus.l10.service.UsersCachedService;
import ru.otus.l10.service.UsersService;
import ru.otus.l10.message.Addressee;
import ru.otus.l10.message.FrontService;
import ru.otus.l10.message.MessageSystem;
import ru.otus.l10.message.MessageSystemContext;
import ru.otus.l10.web.TemplateProccesor;


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
    public TemplateProccesor templateProccesor() {
        return new TemplateProccesor();
    }

    @Bean
    public RandomGetter randomGetter() {
        return new RandomGetter(usersCachedService());
    }

    @Bean
    public FrontService frontService() {
        return new FrontService();
    }

    @Bean
    public MessageSystemContext messageSystemContext() {
        FrontService frontService = frontService();
        Addressee backService = cacheEngine();
        MessageSystem messageSystem = new MessageSystem();
        messageSystem.addAddressee(frontService);
        messageSystem.addAddressee(backService);
        messageSystem.start();
        MessageSystemContext context = new MessageSystemContext(messageSystem, backService.getAddress(), frontService.getAddress());
        frontService.setContext(context);
        return context;
    }


}
