package io.muzoo.ooc.webapp.basic.security;

import io.muzoo.ooc.webapp.basic.database.MySQLJava;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityService {

    private final MySQLJava database = MySQLJava.getInstance();

    private static final SecurityService securityService = new SecurityService();

    public static SecurityService getInstance() {
        return securityService;
    }

    public String getCurrentrUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object usernameObj = session.getAttribute("username");
        return (String) usernameObj;
    }

    public boolean isAuthorize(HttpServletRequest request) {
        String username = getCurrentrUsername(request);
        return (username != null && database.isUser(username));

    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        if (database.isLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            int id = getId(username);
            session.setAttribute("sessionId", id);
            return true;
        } else {
            return false;
        }
    }

    public String getName(String username){
        return database.getName(username);
    }

    public String getName(int id){
        return database.getName(id);
    }

    public int getId(String username){
        return database.getId(username);
    }

    public boolean addNewUser(String username, String password, String name){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return database.addNewUser(username, hashedPassword, name);
    }

    public boolean removeUser(int id){
        return database.deleteUser(id);
    }

    public boolean editUser(int id, String username, String password, String name){
        return database.editUser(id, username, password, name);
    }

    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("sessionId");
        session.invalidate();
    }
}
