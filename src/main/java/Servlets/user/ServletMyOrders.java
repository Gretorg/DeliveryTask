package Servlets.user;

import db.DBManager;
import db.entity.Delivery;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * My orders servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/myOrders")
public class ServletMyOrders extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletMyOrders");
    private static final DBManager dbManager = DBManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        HttpSession session = request.getSession(false);
        int userId = 0;
        int status = Integer.parseInt(request.getParameter("status"));

        if(session != null){
            userId = (int) session.getAttribute("id");
        }

        List<Delivery> deliveryList = null;

        try {
            deliveryList = dbManager.findDeliveryById(connection,userId,status);
            logger.info("Find all deliveries by user id and status of delivery");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        request.setAttribute("status",status);
        request.setAttribute("deliveryList",deliveryList);
        getServletContext().getRequestDispatcher("/myOrders.jsp").forward(request, response);

    }
}
