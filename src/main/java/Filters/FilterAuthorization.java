package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Security filer. Filter does not allowed
 unregistered user to visit user pages
 *
 * @author  Vlad Salimovskyi
 */
@WebFilter(filterName = "FilterAuthorization")
public class FilterAuthorization implements Filter {


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession session = httpReq.getSession();

        Object role = session.getAttribute("role");

        if (role == null) {
            httpResp.sendRedirect("http://localhost:8081/DeliveryTask_war/home");
        }
        else {
            int roleId = (int) session.getAttribute("role");
            if(roleId == 2){
                chain.doFilter(req, resp);
            }else{
                httpResp.sendRedirect("http://localhost:8081/DeliveryTask_war/home");
            }

        }
    }

    public void init(FilterConfig config) {

    }

}
