package dao;


import model.UserDataSet;
import org.hibernate.Session;

import java.util.List;

public class UsersDAO extends AbstractDAO<UserDataSet> implements DAO<UserDataSet> {

    public UsersDAO(Session session) {
        super(session);
    }

}
