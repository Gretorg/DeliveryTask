package Listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;

/**
 * Logger listener
 *
 * @author  Vlad Salimovskyi
 */
@WebListener()
public class ListenerLogger implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger LOG = Logger.getLogger(ListenerLogger.class);

    public void contextDestroyed(ServletContextEvent event) {

        log("Servlet context destruction starts");
        log("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {

        log("Servlet context initialization starts");
        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        log("Servlet context initialization finished");
    }

    private void initLog4J(ServletContext servletContext) {

        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
            ex.printStackTrace();
        }
        log("Log4J initialization finished");
    }


    private void log(String msg) {
        System.out.println("[ContextListener]" + msg);
    }
}
