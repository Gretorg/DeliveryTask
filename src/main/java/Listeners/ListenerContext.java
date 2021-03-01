package Listeners;

import db.DBManager;
import db.entity.Routes;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Context listener
 *
 * @author  Vlad Salimovskyi
 */
@WebListener()
public class ListenerContext implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {


    public static Logger logger = Logger.getLogger(ListenerContext.class);
    private final DBManager dbManager = DBManager.getInstance();

    public void contextInitialized(ServletContextEvent event) {

        Connection connection = dbManager.getConnection();
        ServletContext sc = event.getServletContext();
        @SuppressWarnings("unchecked")
        List<Routes> routes = (List<Routes>) sc.getAttribute("routes");

        if(routes == null){
            try {
                routes = dbManager.findAllRoutes(connection);
                sc.setAttribute("routes",routes);

            } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        }
    }

}
