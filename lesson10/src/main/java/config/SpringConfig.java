package config;

import cache.CacheEngine;
import cache.CacheSettings;
import model.UserDataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.RandomGetter;
import service.UsersCachedService;
import service.UsersService;
import web.TemplateProccesor;


@Configuration
public class SpringConfig {

    @Bean
    public UsersCachedService usersCachedService() {
        return new UsersCachedService(new UsersService(), cacheEngine());
    }

    @Bean
    public CacheEngine<Long, UserDataSet> cacheEngine() {
        return new CacheEngine<Long, UserDataSet>(new CacheSettings(10, 0, 0, false));
    }

   @Bean
    public TemplateProccesor templateProccesor() {
        return new TemplateProccesor();
    }

    @Bean
    public RandomGetter randomGetter() {
        return new RandomGetter(usersCachedService());
    }
}
