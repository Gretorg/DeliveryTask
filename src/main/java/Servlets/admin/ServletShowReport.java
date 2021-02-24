package Servlets.admin;

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
import java.util.List;

/**
 * Show report servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/showReport")
public class ServletShowReport extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletShowReport");
    private final DBManager dbManager = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        List<String> dateList = null;
        List<String> cityList = null;

        try {
            dateList = dbManager.findDistinctDateSend(connection);
            logger.info("Find all dates of sending the vantage");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        try {
            cityList = dbManager.findDistinctCityFrom(connection);
            logger.info("Find all cities where vantage was sent from");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        request.setAttribute("dateList",dateList);
        request.setAttribute("cityList",cityList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reportsPage.jsp");
        dispatcher.forward(request, response);


    }
}
