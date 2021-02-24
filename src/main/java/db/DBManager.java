package db;

import Listeners.ListenerLogger;
import db.entity.Cargo;
import db.entity.Routes;
import db.entity.Delivery;
import db.entity.Users;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DB manager. Works with mysql DB. Required DAO methods are defined only.
 *
 * @author  Vlad Salimovskyi
 */
public class DBManager {


    public static Logger logger = Logger.getLogger("DBManager");

    private static final String URL = "jdbc:mysql://localhost/delivery?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final String INSERT_NEW_USER = "insert into users values(null,?,?,?,?,?,?,?);";
    private static final String INSERT_NEW_CARGO = "insert into cargo values(null,?,?,?,?,?);";
    private static final String INSERT_NEW_DELIVERY = "insert into delivery values(null,?,?,?,?,?,?,?,?,?,1);";

    private static final String SELECT_DISTANCE_BY_ID = "select route_distance from routes where route_id=?;";
    private static final String SELECT_ROLE_BY_EMAIL_PASS = "select users.role_id from users " +
            "where users.user_email=? " +
            "and users.user_password=?;";
    private static final String SELECT_USER_ID_BY_EMAIL = "select users.user_id from users where users.user_email=?;";
    private static final String SELECT_SALT_BY_EMAIL = "select users.user_salt from users where users.user_email=?;";
    private static final String SELECT_EMAIL_PASS = "select users.user_email, users.user_password from users;";
    private static final String SELECT_EMAIL_FROM_USERS="select users.user_email from users;";
    private static final String SELECT_ALL_FROM_ROUTE = "select * from routes order by city_from;";
    private static final String SELECT_DELIVERY_BY_USER_ID = "select delivery.*, routes.*, status.delivery_status\n" +
            "from delivery\n" +
            "inner join routes \n" +
            "on delivery.route_id = routes.route_id \n" +
            "inner join status \n" +
            "on delivery.status_id = status.status_id\n" +
            "where delivery.user_id = ?\n" +
            "and delivery.status_id = ?;";
    private static final String SELECT_DELIVERY_BY_STATUS_PENDING = "select delivery.*, routes.*, status.delivery_status\n" +
            "from delivery\n" +
            "inner join routes \n" +
            "on delivery.route_id = routes.route_id \n" +
            "inner join status \n" +
            "on delivery.status_id = status.status_id\n" +
            "where delivery.status_id = ?;";
    private static final String UPDATE_ORDER = "update delivery set delivery.status_id =? where delivery.delivery_id=?;";
    private static final String SELECT_DISTINCT_SEND_DATE = "select distinct delivery.send_date from delivery;";
    private static final String SELECT_DISTINCT_CITY_FROM = "select distinct routes.city_from \n" +
            "from routes\n" +
            "inner join delivery\n" +
            "on routes.route_id = delivery.route_id\n" +
            "order by routes.city_from;";
    private static final String SELECT_REPORTS = "select delivery.*, routes.*, status.delivery_status\n" +
            "from delivery\n" +
            "inner join routes\n" +
            "on delivery.route_id = routes.route_id\n" +
            "inner join status\n" +
            "on delivery.status_id = status.status_id\n" +
            "where delivery.status_id = 3\n" +
            "and delivery.send_date = ?\n" +
            "and routes.city_from =?;";

    private static final String SELECT_DELIVERY_BY_STATUS_PENDING_PAGINATION = "select delivery.*, routes.*, status.delivery_status\n" +
            "from delivery\n" +
            "inner join routes\n" +
            "on delivery.route_id = routes.route_id\n" +
            "inner join status\n" +
            "on delivery.status_id = status.status_id\n" +
            "where delivery.status_id = 3\n" +
            "limit ?, 5;";

    private static final String COUNT_OF_ROWS_IN_QUERY = "SELECT COUNT(1) AS total\n" +
            "from delivery\n" +
            "inner join routes\n" +
            "on delivery.route_id = routes.route_id\n" +
            "inner join status\n" +
            "on delivery.status_id = status.status_id\n" +
            "where delivery.status_id = 3;";

    private static DBManager dbManager;

    private DBManager(){}

    public static DBManager getInstance(){

        if(dbManager==null){
            dbManager = new DBManager();
        }
        return dbManager;
    }

    /**
     * Returns a DB connection.
     *
     * @return DB connection.
     */
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

    /**
     * Insert a new user to DB.
     */
    public boolean insertUser(Connection connection, Users user){

        boolean insert = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getBirthDate());
            preparedStatement.setInt(7, user.getRoleId());
            insert = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return insert;
    }

    /**
     * Insert a new cargo to DB.
     *
     * @return cargoId
     */
    public int insertCargo(Connection connection, Cargo cargo){

        int createdId = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_CARGO,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, cargo.getWeight());
            preparedStatement.setInt(2, cargo.getLength());
            preparedStatement.setInt(3, cargo.getWidth());
            preparedStatement.setInt(4, cargo.getHeight());
            preparedStatement.setString(5, cargo.getDescription());
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Failed");
            }

            try(ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if(rs.next()){
                    createdId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return createdId;
    }

    /**
     * Insert a new delivery to DB.
     */
    public boolean insertDelivery(Connection connection, Delivery delivery){

        boolean insert = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_DELIVERY)) {
            preparedStatement.setInt(1, delivery.getCargoId());
            preparedStatement.setInt(2, delivery.getRouteId());
            preparedStatement.setInt(3, delivery.getUserId());
            preparedStatement.setString(4, delivery.getReceiverName());
            preparedStatement.setString(5, delivery.getReceiverSurname());
            preparedStatement.setString(6, delivery.getAddress());
            preparedStatement.setString(7, delivery.getSendDate());
            preparedStatement.setString(8, delivery.getDeliveryDate());
            preparedStatement.setInt(9, delivery.getPrice());
            insert = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return insert;
    }

    /**
     * Returns user`s email and password
     *
     * @return List of users entities.
     */
    public List<Users> findUsersInfo(Connection connection) throws SQLException {

        ResultSet rs = null;
        List<Users> user = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_PASS)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String email = rs.getString(Fields.USER_EMAIL);
                String password = rs.getString(Fields.USER_PASSWORD);
                user.add(new Users(email,password));
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return user;
    }

    /**
     * Returns user`s email
     *
     * @return List of users entities.
     */
    public List<String> findUsersEmails(Connection connection) throws SQLException {

        ResultSet rs = null;
        List<String> userEmails = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_FROM_USERS)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String email = rs.getString(Fields.USER_EMAIL);
                userEmails.add(email);
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return userEmails;
    }

    /**
     * Returns user`s salt by email
     *
     * @return Users`s salt
     */
    public String findSaltByEmail(Connection connection, String email) throws SQLException {

        String salt = null;
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SALT_BY_EMAIL)) {
            preparedStatement.setString(1,email);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                salt = rs.getString(Fields.USER_SALT);
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return salt;
    }

    /**
     * Returns user`s id by email
     *
     * @return User`s id
     */
    public int findIdByEmail(Connection connection, String email) throws SQLException {

        int id = 0;
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID_BY_EMAIL)) {
            preparedStatement.setString(1,email);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = Integer.parseInt(rs.getString(Fields.USER_ID));
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return id;
    }

    /**
     * Returns user`s role by email and password
     *
     * @return User`s role
     */
    public int findRoleByEmail(Connection connection, String email,String password) throws SQLException {

        int role = 0;
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_EMAIL_PASS)) {
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                role = rs.getInt(Fields.ROLE_ID);
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return role;
    }

    /**
     * Returns route`s distance by id
     *
     * @return Route`s distance
     */
    public int findDistanceById(Connection connection, int id) throws SQLException {

        int distance = 0;
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTANCE_BY_ID)) {
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                distance = Integer.parseInt(rs.getString(Fields.DISTANCE));
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return distance;
    }

    /**
     * Returns all routes.
     *
     * @return List of routes entities.
     */
    public List<Routes> findAllRoutes(Connection connection) throws SQLException {

        ResultSet rs = null;
        List<Routes> routes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_ROUTE)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(Fields.ROUTE_ID);
                String cityFrom = rs.getString(Fields.CITY_FROM);
                String cityTo = rs.getString(Fields.CITY_TO);
                int distance = rs.getInt(Fields.DISTANCE);
                routes.add(new Routes(id,cityFrom,cityTo,distance));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return routes;
    }

    /**
     * Returns delivery by user id and status of delivery.
     *
     * @return List of delivery entities.
     */
    public List<Delivery> findDeliveryById(Connection connection, int userId, int statusDelivery) throws SQLException {

        ResultSet rs = null;
        List<Delivery> deliveryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_BY_USER_ID)) {
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,statusDelivery);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                int delivery_id = rs.getInt(Fields.DELIVERY_ID);
                String address = rs.getString(Fields.ADDRESS);
                String name = rs.getString(Fields.RECEIVER_NAME);
                String lastName = rs.getString(Fields.RECEIVER_LAST_NAME);
                String sendDate = rs.getString(Fields.SEND_DATE);
                String deliveryDate = rs.getString(Fields.DELIVERY_DATE);
                int price = rs.getInt(Fields.PRICE);
                String cityFrom = rs.getString(Fields.CITY_FROM);
                String cityTo = rs.getString(Fields.CITY_TO);
                String status = rs.getString("delivery_status");
                deliveryList.add(new Delivery(delivery_id,address,name,lastName,sendDate,deliveryDate,price,cityFrom,cityTo,status));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return deliveryList;
    }

    /**
     * Returns delivery by status of delivery.
     *
     * @return List of delivery entities.
     */
    public List<Delivery> findUsersOrders(Connection connection, int statusDelivery) throws SQLException {

        ResultSet rs = null;
        List<Delivery> deliveryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_BY_STATUS_PENDING)) {
            preparedStatement.setInt(1, statusDelivery);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                int delivery_id = rs.getInt(Fields.DELIVERY_ID);
                String address = rs.getString(Fields.ADDRESS);
                String name = rs.getString(Fields.RECEIVER_NAME);
                String lastName = rs.getString(Fields.RECEIVER_LAST_NAME);
                String sendDate = rs.getString(Fields.SEND_DATE);
                String deliveryDate = rs.getString(Fields.DELIVERY_DATE);
                int price = rs.getInt(Fields.PRICE);
                String cityFrom = rs.getString(Fields.CITY_FROM);
                String cityTo = rs.getString(Fields.CITY_TO);
                String status = rs.getString("delivery_status");
                deliveryList.add(new Delivery(delivery_id,address,name,lastName,sendDate,deliveryDate,price,cityFrom,cityTo,status));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return deliveryList;
    }

    /**
     * Update user`s order.
     *
     * @param statusId,deliveryId
     *            user`s order to update.
     */
    public void updateOrderBy(Connection connection, int statusId, int deliveryId) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {
            statement.setInt(1, statusId);
            statement.setInt(2, deliveryId);
            statement.executeUpdate();
        }
    }

    /**
     * Returns cities where it was delivered from.
     *
     * @return List of cities.
     */
    public List<String> findDistinctCityFrom(Connection connection) throws SQLException {

        ResultSet rs = null;
        List<String> cityList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTINCT_CITY_FROM)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String cityFrom = rs.getString(Fields.CITY_FROM);
                cityList.add(cityFrom);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return cityList;
    }

    /**
     * Returns dates when it was delivered.
     *
     * @return List of dates.
     */
    public List<String> findDistinctDateSend(Connection connection) throws SQLException {

        ResultSet rs = null;
        List<String> dateList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTINCT_SEND_DATE)) {
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String cityFrom = rs.getString(Fields.SEND_DATE);
                dateList.add(cityFrom);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return dateList;
    }

    /**
     * Returns reports by date and city.
     *
     * @return List of delivery entities.
     */
    public List<Delivery> findReports(Connection connection, String date, String city) throws SQLException {

        ResultSet rs = null;
        List<Delivery> deliveryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REPORTS)) {
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, city);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                int delivery_id = rs.getInt(Fields.DELIVERY_ID);
                String address = rs.getString(Fields.ADDRESS);
                String name = rs.getString(Fields.RECEIVER_NAME);
                String lastName = rs.getString(Fields.RECEIVER_LAST_NAME);
                String sendDate = rs.getString(Fields.SEND_DATE);
                String deliveryDate = rs.getString(Fields.DELIVERY_DATE);
                int price = rs.getInt(Fields.PRICE);
                String cityFrom = rs.getString(Fields.CITY_FROM);
                String cityTo = rs.getString(Fields.CITY_TO);
                String status = rs.getString("delivery_status");
                deliveryList.add(new Delivery(delivery_id,address,name,lastName,sendDate,deliveryDate,price,cityFrom,cityTo,status));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return deliveryList;
    }

    /**
     * Returns reports by pagination.
     *
     * @return List of delivery entities.
     */
    public List<Delivery> findUsersOrdersPagination(Connection connection, int start) throws SQLException {

        ResultSet rs = null;
        List<Delivery> deliveryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_BY_STATUS_PENDING_PAGINATION)) {
            preparedStatement.setInt(1, start);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                int delivery_id = rs.getInt(Fields.DELIVERY_ID);
                String address = rs.getString(Fields.ADDRESS);
                String name = rs.getString(Fields.RECEIVER_NAME);
                String lastName = rs.getString(Fields.RECEIVER_LAST_NAME);
                String sendDate = rs.getString(Fields.SEND_DATE);
                String deliveryDate = rs.getString(Fields.DELIVERY_DATE);
                int price = rs.getInt(Fields.PRICE);
                String cityFrom = rs.getString(Fields.CITY_FROM);
                String cityTo = rs.getString(Fields.CITY_TO);
                String status = rs.getString("delivery_status");
                deliveryList.add(new Delivery(delivery_id,address,name,lastName,sendDate,deliveryDate,price,cityFrom,cityTo,status));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return deliveryList;
    }

    /**
     * Returns amount of rows in sql query.
     *
     * @return Count of rows.
     */
    public int findUsersOrdersCount(Connection connection) throws SQLException {

        ResultSet rs = null;
        int count = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_OF_ROWS_IN_QUERY)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                count = Integer.parseInt(rs.getString("total"));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }finally {
            assert rs != null;
            rs.close();
        }
        return count;
    }
}
