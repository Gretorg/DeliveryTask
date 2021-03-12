package Servlets.admin;

import db.DBManager;
import db.entity.delivery.Delivery;
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
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        String date = encode(request.getParameter("date"), SCR_PAGE, DST_PAGE);
        String city = encode(request.getParameter("city"), SCR_PAGE, DST_PAGE);
        int show = 1;

        List<Delivery> deliveryList = null;
        List<String> dateList = null;
        List<String> cityList = null;

        try {
            deliveryList = dbManager.findReports(connection, date, city);
            logger.info("Find reports by date and send city");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

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
        request.setAttribute("show", show);
        request.setAttribute("deliveryList", deliveryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reportsPage.jsp");
        dispatcher.forward(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/reportsPage.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Get parameter from jsp with UTF-8
     */
    public String encode(String src, String defpage, String codepage) {
        String answer;
        try {
            answer = new String(src.getBytes(defpage), codepage);
        } catch (Throwable e) {
            answer = src;
        }
        return answer;
    }
}
