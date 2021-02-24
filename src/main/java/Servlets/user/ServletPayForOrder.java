package Servlets.user;

import db.DBManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Pay for order servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/payForOrder")
public class ServletPayForOrder extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletPayForOrder");
    private static final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        int deliveryId = Integer.parseInt(request.getParameter("id"));
        int status = 3;

        try {
            dbManager.updateOrderBy(connection,status,deliveryId);
            logger.info("Update order status (admin)");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        String path = request.getContextPath() + "/myOrders?status=2";
        response.sendRedirect(path);
    }
}
