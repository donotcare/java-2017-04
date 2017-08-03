package ru.otus.l16.backend.dao;


import org.hibernate.Session;
import ru.otus.l16.backend.model.UserDataSet;

public class UsersDAO extends AbstractDAO<UserDataSet> implements DAO<UserDataSet> {

    public UsersDAO(Session session) {
        super(session);
    }

}
