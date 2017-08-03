package ru.otus.l16.backend.service;


import org.hibernate.Session;
import ru.otus.l16.backend.AbstractDbService;
import ru.otus.l16.backend.dao.DAO;
import ru.otus.l16.backend.dao.UsersDAO;
import ru.otus.l16.backend.model.UserDataSet;

public class UsersService extends AbstractDbService<UserDataSet> {
    @Override
    protected DAO<UserDataSet> getDao(Session session) {
        return new UsersDAO(session);
    }
}
