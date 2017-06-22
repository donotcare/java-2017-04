import config.DBConfigHibernate;
import model.PhoneDataSet;
import model.UserDataSet;
import org.junit.Test;
import service.DBService;
import service.UsersService;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class HibernateTest {

    @Test
    public void persistAndGet() throws SQLException {
        DBService<UserDataSet> service = new UsersService();
        UserDataSet user = new UserDataSet(10, "Ivan");
        PhoneDataSet phone = new PhoneDataSet("29329323", 212);
        user.addPhone(phone);
        service.save(user);
        user = service.read(user.getId());
        assertEquals("Ivan", user.getPersonName());
        assertEquals(phone, user.getPhones().get(0));
        DBConfigHibernate.shutdown();
    }
}
