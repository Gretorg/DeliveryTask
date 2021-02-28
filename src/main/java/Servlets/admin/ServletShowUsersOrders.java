package Servlets.admin;

import db.DBManager;
import db.entity.Delivery;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Show users orders servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/showUserOrders")
public class ServletShowUsersOrders extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletShowUsersOrders");
    private static final DBManager dbManager = DBManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        List<Delivery> deliveryList = null;
        int status = Integer.parseInt(request.getParameter("status"));

        try {
            deliveryList = dbManager.findUsersOrders(connection, status);
            logger.info("Find all users orders by status of delivery"+status);
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        request.setAttribute("status",status);
        request.setAttribute("deliveryList",deliveryList);
        getServletContext().getRequestDispatcher("/userOrders.jsp").forward(request, response);
    }
}
