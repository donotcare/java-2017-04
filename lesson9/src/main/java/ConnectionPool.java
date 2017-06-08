import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:create.sql'", "test", "");

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
