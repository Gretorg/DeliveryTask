import db.DBManager;
import db.entity.Cargo;
import db.entity.ConnectionDB;
import db.entity.delivery.Delivery;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManagerTest implements ConnectionDB {

    private static final String URL = "jdbc:mysql://localhost/deliveryTest?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final DBManager dbManager = DBManager.getInstance();
    public static Logger logger = Logger.getLogger("DBManagerTest");

    @Override
    public Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Connected to DB");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return connection;
    }


    @Test
    public void findSaltByEmailTest() throws SQLException {

        Connection connection = getConnection();
        String expected = "asdsad";
        String actual = dbManager.findSaltByEmail(connection, "email@gmail.com");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findIdByEmailTest() throws SQLException {

        Connection connection = getConnection();
        int expected = 1;
        int actual = dbManager.findIdByEmail(connection, "admin@gmail.com");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findRoleByEmailTest() throws SQLException {

        Connection connection = getConnection();
        int expected = 1;
        int actual = dbManager.findRoleByEmail(connection, "admin@gmail.com","B4BB0CED096BCBE452156EB8D26BDDA7C0DDE0A7F76F79C9D0456133C623CBBF");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findDistanceByIdTest() throws SQLException {

        Connection connection = getConnection();
        int expected = 429;
        int actual = dbManager.findDistanceById(connection, 1);
        Assert.assertEquals(actual, expected);
    }
}
