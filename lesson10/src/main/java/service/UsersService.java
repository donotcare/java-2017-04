package service;


import dao.DAO;
import dao.UsersDAO;
import model.UserDataSet;
import org.hibernate.Session;

public class UsersService extends AbstractDbService<UserDataSet> {
    @Override
    protected DAO<UserDataSet> getDao(Session session) {
        return new UsersDAO(session);
    }
}
