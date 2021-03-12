package Servlets.admin;

import db.DBManager;
import db.entity.delivery.Delivery;
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
 * Show users orders with pagination
 *
 * @author Vlad Salimovskyi
 */
@WebServlet(name = "/showUserOrdersPagination")
public class ServletShowOrdersPagination extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletShowOrdersPagination");
    private static final DBManager dbManager = DBManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        List<Delivery> deliveryList = null;
        String order = request.getParameter("order");
        int pageId = Integer.parseInt(request.getParameter("page"));

        int countPages;

        /* count of rows in sql query*/
        int countRows = 0;

        /* total rows to show from query*/
        int total = 5;

        /* status of delivery*/
        int status = 3;

        /* start query limit from*/
        int start = 0;

        try {
            countRows = DBManager.getInstance().findUsersOrdersCount(connection);
            logger.info("Find counts of rows in sql query");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        countPages = countRows / total + 1;

        if (pageId != 1) {
            start = total * (pageId - 1);
        }

        if (order != null) {
            try {
                deliveryList = dbManager.findUsersOrderBy(connection, start, order);
                logger.info("Find users orders with pagination order by "+order);
            } catch (SQLException e) {
                logger.debug(e.getMessage());
            }
        }
        else{
            try {
                deliveryList = dbManager.findUsersOrdersPagination(connection, start);
                logger.info("Find users orders with pagination");
            } catch (SQLException e) {
                logger.debug(e.getMessage());
            }
        }

        request.setAttribute("order", order);
        request.setAttribute("countPages", countPages);
        request.setAttribute("status", status);
        request.setAttribute("deliveryList", deliveryList);
        getServletContext().getRequestDispatcher("/userOrders.jsp").forward(request, response);
    }
}
