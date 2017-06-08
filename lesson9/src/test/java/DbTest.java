import model.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DbTest {

    @Test
    public void persistAndGet() throws SQLException {
        Executor executor = new Executor();
        User user = new User(25, "Ivan");
        executor.save(user);
        assertEquals(user, executor.load(user.getId(), user.getClass()));
        user.setAge(30);
        executor.save(user);
        assertEquals(30, executor.load(user.getId(), user.getClass()).getAge());
    }

    @Test
    public void getByNoneExistId() throws SQLException {
        Executor executor = new Executor();
        User user = new User(25, "Ivan");
        executor.save(user);
        user = executor.load(user.getId() + 1, user.getClass());
        assertNull(user);
    }

}
