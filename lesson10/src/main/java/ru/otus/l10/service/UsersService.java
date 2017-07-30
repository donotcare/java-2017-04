package ru.otus.l10.service;


import ru.otus.l10.AbstractDbService;
import ru.otus.l10.dao.DAO;
import ru.otus.l10.dao.UsersDAO;
import ru.otus.l10.model.UserDataSet;
import org.hibernate.Session;

public class UsersService extends AbstractDbService<UserDataSet> {
    @Override
    protected DAO<UserDataSet> getDao(Session session) {
        return new UsersDAO(session);
    }
}
