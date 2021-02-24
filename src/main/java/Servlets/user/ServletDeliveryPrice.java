package Servlets.user;

import db.DBManager;
import db.Fields;
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
 * Show delivery price servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/price")
public class ServletDeliveryPrice extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletDeliveryPrice");
    private static final DBManager dbManager = DBManager.getInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        int routeId = Integer.parseInt(request.getParameter(Fields.ROUTE_ID));
        int weight = Integer.parseInt(request.getParameter(Fields.WEIGHT));
        int length = Integer.parseInt(request.getParameter(Fields.LENGTH));
        int width = Integer.parseInt(request.getParameter(Fields.WIDTH));
        int height = Integer.parseInt(request.getParameter(Fields.HEIGHT));
        int distance = 0;

        try {
            distance = dbManager.findDistanceById(connection,routeId);
            logger.info("Find distance by route id");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        int price = (int) getPrice(distance,weight,length,width,height);
        request.setAttribute("price",price);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
        dispatcher.forward(request, response);

    }

    /**
     * Forming a price for delivery
     */
    public static double getPrice(int distance, int weight, int length, int width, int height){

        return distance * 0.1 + weight + length * 0.1 + width * 0.1 + height * 0.1;
    }
}
