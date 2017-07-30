package ru.otus.l10.dao;


import ru.otus.l10.model.UserDataSet;
import org.hibernate.Session;

public class UsersDAO extends AbstractDAO<UserDataSet> implements DAO<UserDataSet> {

    public UsersDAO(Session session) {
        super(session);
    }

}
