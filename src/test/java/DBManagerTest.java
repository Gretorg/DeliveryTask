import db.DBManager;
import db.entity.Cargo;
import db.entity.Delivery;
import db.entity.Users;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManagerTest {

    private static final String URL = "jdbc:mysql://localhost/deliveryTest?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final DBManager dbManager = DBManager.getInstance();
    public static Logger logger = Logger.getLogger("DBManagerTest");

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
    public void insertUser(){

        Connection connection = getConnection();
        boolean actual = dbManager.insertUser(connection,new Users("email@gmail.com","123",
                "asdsad", "Ivan","Ivanov","2000-10-10",2));
        Assert.assertTrue(actual);
    }

    @Test
    public void insertCargoAndDelivery(){

        Connection connection = getConnection();
        Cargo cargo = new Cargo(2,20,20,20,"Сейф");
        dbManager.insertCargo(connection, cargo);
        cargo.setId(dbManager.insertCargo(connection, cargo));
        boolean actual = dbManager.insertDelivery(connection,new Delivery(1, cargo.getId(), 1,
                "Ivanov", "Ivanov","Street",
                "2021-02-24","2021-02-26",200));
        Assert.assertTrue(actual);
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
