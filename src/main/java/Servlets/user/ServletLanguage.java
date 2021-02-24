package Servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Language servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "ServletInternalization")
public class ServletLanguage extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String localeToSet = request.getParameter("lang");
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
        }
        getServletContext().getRequestDispatcher("/home").forward(request, response);

    }
}
