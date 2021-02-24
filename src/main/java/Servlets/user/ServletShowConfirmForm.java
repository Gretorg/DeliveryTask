package Servlets.user;

import db.DBManager;
import db.Fields;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Show confirm form servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/showConfirmForm")
public class ServletShowConfirmForm extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletShowConfirmForm");
    private static final DBManager dbManager = DBManager.getInstance();
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        int routeId = Integer.parseInt(request.getParameter(Fields.ROUTE_ID));
        int weight = Integer.parseInt(request.getParameter(Fields.WEIGHT));
        int length = Integer.parseInt(request.getParameter(Fields.LENGTH));
        int width = Integer.parseInt(request.getParameter(Fields.WIDTH));
        int height = Integer.parseInt(request.getParameter(Fields.HEIGHT));
        String description = encode(request.getParameter(Fields.DESCRIPTION),SCR_PAGE,DST_PAGE);
        String address = encode(request.getParameter(Fields.ADDRESS),SCR_PAGE,DST_PAGE);
        int distance = 0;

        try {
            distance = dbManager.findDistanceById(connection,routeId);
            logger.info("Find distance by route id");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        int price = (int) getPrice(distance,weight,length,width,height);
        String firstName = encode(request.getParameter(Fields.FIRST_NAME),SCR_PAGE,DST_PAGE);
        String lastName = encode(request.getParameter(Fields.LAST_NAME),SCR_PAGE,DST_PAGE);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateWithoutTime = dateFormat.format(date);
        String dateAddTwo = dateFormat.format(addDays(date,2));

        request.setAttribute("route_id",routeId);
        request.setAttribute("date",dateWithoutTime);
        request.setAttribute("date2",dateAddTwo);
        request.setAttribute("weight",weight);
        request.setAttribute("length",length);
        request.setAttribute("width",width);
        request.setAttribute("height",height);
        request.setAttribute("description",description);
        request.setAttribute("firstName",firstName);
        request.setAttribute("lastName",lastName);
        request.setAttribute("address",address);
        request.setAttribute("price",price);

        getServletContext().getRequestDispatcher("/confirmDelivery.jsp").forward(request, response);
    }

    /**
     * Forming a price for delivery
     */
    public static double getPrice(int distance, int weight, int length, int width, int height){

        return distance * 0.1 + weight + length * 0.1 + width * 0.1 + height * 0.1;
    }

    /**
     * Get parameter from jsp with UTF-8
     */
    public String encode(String src, String defpage ,String codepage) {
        String answer;
        try {
            answer= new String(src.getBytes(defpage), codepage);
        } catch (Throwable e){
            answer=src;
        }
        return answer;
    }

    /**
     * Add two days
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
