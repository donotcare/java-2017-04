package ru.otus.l16.backend.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import ru.otus.l16.backend.model.DataSet;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDAO<T extends DataSet> implements DAO<T> {
    private final Class<T> persistentClass;
    private Session session;

    public AbstractDAO(Session session) {
        this.session = session;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T dataSet) {
        session.save(dataSet);
    }

    public T read(long id) {
        return session.load(persistentClass, id);
    }

    public List<T> readAll() {
        Criteria criteria = session.createCriteria(persistentClass);
        return criteria.list();
    }
}
