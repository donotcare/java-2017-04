package ru.otus.l10.service;

import ru.otus.l10.model.UserDataSet;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomGetter {
    private final UsersCachedService cachedService;

    public RandomGetter(UsersCachedService cachedService) {
        this.cachedService = cachedService;
    }

    @PostConstruct
    private void dataFill() {
        UserDataSet user = new UserDataSet(10, "Ivan");
        cachedService.save(user);
        randomGet();
    }

    private void randomGet() {
        Random random = new Random();
        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            public void run() {
                cachedService.read(random.nextInt(10));
            }
        };
        timer.schedule(task, 1000, 1000);
    }
}
