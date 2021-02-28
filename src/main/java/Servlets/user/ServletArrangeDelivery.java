package Servlets.user;

import db.DBManager;
import db.entity.Routes;
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
import java.util.List;

/**
 * Arrange delivery servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/arrangeDelivery")
public class ServletArrangeDelivery extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletArrangeDelivery");
    private final DBManager dbManager = DBManager.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        List<Routes> routes = null;
        try {
            routes = dbManager.findAllRoutes(connection);
            logger.info("Find all routes");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        request.setAttribute("routes",routes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("arrangeDelivery.jsp");
        dispatcher.forward(request, response);
    }
}
