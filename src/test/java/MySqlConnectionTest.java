import books.database.MySqlImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MySqlConnectionTest {
    private static Connection connection;

    @Before
    public void init() throws SQLException {
        connection = MySqlImpl.getNewConnection();
    }

    @Test
    public void shouldGetJdbkConnection() throws Exception {
        assertTrue(connection.isValid(1));
        assertFalse(connection.isClosed());
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }

}
