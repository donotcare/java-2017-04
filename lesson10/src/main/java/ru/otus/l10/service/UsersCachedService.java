package ru.otus.l10.service;

import ru.otus.l10.cache.CacheElement;
import ru.otus.l10.cache.CacheEngine;
import ru.otus.l10.model.UserDataSet;

import java.util.List;

public class UsersCachedService implements DBService<UserDataSet> {
    private final DBService<UserDataSet> service;
    private final CacheEngine<Long, UserDataSet> cache;

    public UsersCachedService(DBService<UserDataSet> service, CacheEngine<Long, UserDataSet> cache) {
        this.service = service;
        this.cache = cache;
    }

    @Override
    public void save(UserDataSet dataSet) {
        service.save(dataSet);
        cache.put(new CacheElement<>(dataSet.getId(), dataSet));
    }

    @Override
    public UserDataSet read(long id) {
        UserDataSet user = cache.get(id);
        if (user == null)
            return service.read(id);
        else
            return user;
    }

    @Override
    public List<UserDataSet> readAll() {
        return service.readAll();
    }
}
