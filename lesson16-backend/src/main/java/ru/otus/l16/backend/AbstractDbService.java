package ru.otus.l16.backend;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.otus.l16.backend.dao.DAO;
import ru.otus.l16.backend.model.DataSet;
import ru.otus.l16.backend.service.DBService;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractDbService<T extends DataSet> implements DBService<T> {

    public void save(T dataSet) {
        try (Session session = DBConfigHibernate.getSession()) {
            DAO<T> dao = getDao(session);
            dao.save(dataSet);
        }
    }

    public T read(long id) {
        return runInSession(session -> {
            DAO<T> dao = getDao(session);
            return dao.read(id);
        });
    }

    public List<T> readAll() {
        return runInSession(session -> {
            DAO<T> dao = getDao(session);
            return dao.readAll();
        });
    }

    protected <R> R runInSession(Function<Session, R> function) {
        try (Session session = DBConfigHibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    protected abstract DAO<T> getDao(Session session);
}
