package Filters;

import db.DBManager;
import db.Fields;
import db.entity.Users;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Login filer. Filter checks user`s
 * email and password
 *
 * @author  Vlad Salimovskyi
 */
@WebFilter(filterName = "FilterLogin")
public class FilterLogin implements Filter {


    public static Logger logger = Logger.getLogger("FilterLogin");
    private static final DBManager dbManager = DBManager.getInstance();
    private static final String SHA= "SHA-256";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        String email = req.getParameter(Fields.USER_EMAIL);
        String password = req.getParameter(Fields.USER_PASSWORD);
        String salt = null;

        try {
            salt = dbManager.findSaltByEmail(connection,email);
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        String concat = password + salt;


        if(checkUser(email,concat)){
            chain.doFilter(req, resp);
        }
        else{
            req.setAttribute("checkUser",checkUser(email,password));
            req.getServletContext().getRequestDispatcher("/wrongEmailPass.jsp").forward(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

    /**
     * Hash password using SHA-256
     */
    public static String hash(String input) throws NoSuchAlgorithmException {

        StringBuilder result = new StringBuilder();
        MessageDigest digest = MessageDigest.getInstance(SHA);
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        for (byte aByteData : hash) {
            result.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1).toUpperCase());
        }
        return result.toString();
    }

    public static boolean checkUser(String email, String concat){

        Connection connection = dbManager.getConnection();
        boolean check = false;
        try {
            Users users1 = new Users(email,hash(concat));
            List<Users> users = dbManager.findUsersInfo(connection);
            Iterator<Users> iterator = users.iterator();
            while (iterator.hasNext()){
                if(iterator.next().equals(users1)){
                    check = true;
                    break;
                }
            }
        } catch (SQLException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }
        return check;
    }

}
