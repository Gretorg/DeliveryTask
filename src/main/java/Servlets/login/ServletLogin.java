package Servlets.login;

import db.DBManager;
import db.Fields;
import db.entity.users.UserBuilder;
import db.entity.users.Users;
import db.entity.users.UsersSecretInfo;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Login servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/login")
public class ServletLogin extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletLogin");
    private static final DBManager dbManager = DBManager.getInstance();
    private static final String SHA= "SHA-256";


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        String email = request.getParameter(Fields.USER_EMAIL);
        String password = request.getParameter(Fields.USER_PASSWORD);
        String salt = (String) request.getAttribute("salt");
        String concat = password + salt;
        Users users = null;
        try {
            users = dbManager.findAllFromUsers(connection,email,hash(concat));
        } catch (SQLException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }

        HttpSession session = request.getSession();
        assert users != null;
        session.setAttribute("role",users.getUsersCommonInfo().getRoleId());
        session.setAttribute("email",email);
        session.setAttribute("id",users.getId());
        getServletContext().getRequestDispatcher("/home").forward(request, response);

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

}
