package Servlets.login;

import db.DBManager;
import db.Fields;
import db.entity.users.UserBuilder;
import db.entity.users.Users;
import db.entity.users.UsersCommonInfo;
import db.entity.users.UsersSecretInfo;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * New user servlet
 *
 * @author  Vlad Salimovskyi
 */
@WebServlet(name = "/newUser")
public class ServletNewUser extends HttpServlet {


    public static Logger logger = Logger.getLogger("ServletNewUser");
    private final DBManager dbManager = DBManager.getInstance();
    private static final String SHA= "SHA-256";
    private static final String SCR_PAGE = "ISO-8859-1";
    private static final String DST_PAGE = "UTF-8" ;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        String email = request.getParameter(Fields.USER_EMAIL);
        String password = request.getParameter(Fields.USER_PASSWORD);
        String salt = getSalt();
        String firstName = encode(request.getParameter(Fields.FIRST_NAME),SCR_PAGE,DST_PAGE);
        String lastName = encode(request.getParameter(Fields.LAST_NAME),SCR_PAGE,DST_PAGE);
        String birthDate = request.getParameter(Fields.BIRTH_DATE);
        int userRole = 2;
        String concat = password + salt;

        if(checkEmail(email)){
            try {
                UserBuilder userBuilder = new UserBuilder();
                userBuilder.setUsersSecretInfo(new UsersSecretInfo(email,hash(concat)));
                userBuilder.setUsersCommonInfo(new UsersCommonInfo(salt,firstName,lastName,birthDate,userRole));
                Users user = userBuilder.getResult();
                dbManager.insertUser(connection,user);
                logger.info("Insert new user");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            Users thisUser = null;
            try {
                thisUser = dbManager.findAllFromUsers(connection,email,hash(concat));
            } catch (SQLException | NoSuchAlgorithmException throwable) {
                throwable.printStackTrace();
            }

            HttpSession session = request.getSession();
            assert thisUser != null;
            session.setAttribute("role",thisUser.getUsersCommonInfo().getRoleId());
            session.setAttribute("email",email);
            session.setAttribute("id",thisUser.getId());
            send(email,"Your password", password);

            getServletContext().getRequestDispatcher("/home").forward(request, response);
        }
        else{
            request.setAttribute("checkEmail",checkEmail(email));
            getServletContext().getRequestDispatcher("/newUser.jsp").forward(request, response);
        }

    }

    /**
     * Hash password using SHA-256
     */
    public String hash(String input) throws NoSuchAlgorithmException {

        StringBuilder res = new StringBuilder();
        MessageDigest digest = MessageDigest.getInstance(SHA);
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        for (byte aByteData : hash) {
            res.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1).toUpperCase());
        }
        return res.toString();
    }

    /**
     * Get random String
     */
    public static String getSalt(){

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        int length = 7;
        for(int i = 0; i < length; i++) {

            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    /**
     * Check if there are users with the same email
     */
    public boolean checkEmail(String email){

        Connection connection = dbManager.getConnection();
        boolean check = true;
        try {
            List<String> userEmails = dbManager.findUsersEmails(connection);
            for(String s : userEmails){
                if(s.equals(email)){
                    check = false;
                    break;
                }
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return check;
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
     * Sending a password to user
     */
    public static void send(String to,String subject, String msg){

        final String user = "delivery.group.0077@gmail.com";
        final String pass = "passworddelivery.group.0077";

        Properties props = new Properties();
        props.put("mail.host","smtp.gmail.com");
        props.put("mail.username","delivery.group.0077@gmail.com");
        props.put("mail.password","passworddelivery.group.0077");
        props.put("mail.defaultEncoding","UTF-8");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.required","false");
        props.put(" mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback","false");
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.socketFactory.port","465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,pass);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}