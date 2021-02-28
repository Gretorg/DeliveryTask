package Servlets.admin;

import db.DBManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Update user oder servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/updateOrder")
public class ServletUpdateOrder extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletUpdateOrder");
    private static final DBManager dbManager = DBManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        int deliveryId = Integer.parseInt(request.getParameter("id"));
        int status = 2;

        try {
            dbManager.updateOrderBy(connection,status,deliveryId);
            logger.info("Update order status (admin)");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        String path = request.getContextPath() + "/showUserOrders?status=1";
        response.sendRedirect(path);
    }
}
