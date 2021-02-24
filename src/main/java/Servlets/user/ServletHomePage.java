package Servlets.user;

import db.entity.Routes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Home page servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/home")
public class ServletHomePage extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = this.getServletContext();

        @SuppressWarnings("unchecked")
        List<Routes> routes = (List<Routes>) servletContext.getAttribute("routes");
        servletContext.setAttribute("routes", routes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("homePage.jsp");
        dispatcher.forward(request, response);
    }


}
