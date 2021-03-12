package Servlets.user;

import db.DBManager;
import db.Fields;
import db.entity.Cargo;
import db.entity.delivery.Delivery;
import db.entity.delivery.DeliveryBuilder;
import db.entity.delivery.DeliveryInsert;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Confirm delivery servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "ServletConfirmDelivery")
public class ServletConfirmDelivery extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletConfirmDelivery");
    private static final DBManager dbManager = DBManager.getInstance();
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8" ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        int weight = Integer.parseInt(request.getParameter(Fields.WEIGHT));
        int length = Integer.parseInt(request.getParameter(Fields.LENGTH));
        int width = Integer.parseInt(request.getParameter(Fields.WIDTH));
        int height = Integer.parseInt(request.getParameter(Fields.HEIGHT));
        String description = encode(request.getParameter(Fields.DESCRIPTION),SCR_PAGE,DST_PAGE);

        int routeId = Integer.parseInt(request.getParameter(Fields.ROUTE_ID));
        int userId = Integer.parseInt(request.getParameter(Fields.USER_ID));
        String firstName = encode(request.getParameter(Fields.FIRST_NAME),SCR_PAGE,DST_PAGE);
        String lastName = encode(request.getParameter(Fields.LAST_NAME),SCR_PAGE,DST_PAGE);
        String sendDate = request.getParameter(Fields.SEND_DATE);
        String deliveryDate = request.getParameter(Fields.DELIVERY_DATE);
        int price = Integer.parseInt(request.getParameter(Fields.PRICE));
        String address = encode(request.getParameter(Fields.ADDRESS),SCR_PAGE,DST_PAGE);

        Cargo cargo = new Cargo(weight,length,width,height,description);
        cargo.setId(dbManager.insertCargo(connection,cargo));
        logger.info("Insert new cargo");

        DeliveryBuilder deliveryBuilder = new DeliveryBuilder();
        deliveryBuilder.setDeliveryInsert(new DeliveryInsert(routeId, cargo.getId(),userId ,firstName, lastName, address, sendDate, deliveryDate, price));
        Delivery delivery = deliveryBuilder.getResult();
        dbManager.insertDelivery(connection,delivery);
        logger.info("Insert new user order");

        String path = request.getContextPath() + "/arrangeDelivery";
        response.sendRedirect(path);

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

}
