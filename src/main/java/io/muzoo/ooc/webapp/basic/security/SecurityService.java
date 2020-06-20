package io.muzoo.ooc.webapp.basic.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import io.muzoo.ooc.webapp.basic.database.MySQLJava;
import org.springframework.security.crypto.bcrypt.*;

public class SecurityService {

    private UserService userService;

    private final MySQLJava database = MySQLJava.getInstance();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public  String getCurrentrUsername(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object usernameObj = session.getAttribute("username");
        return (String) usernameObj;
    }

    public boolean isAuthorize(HttpServletRequest request){
        String username = getCurrentrUsername(request);
        return userService.checkIfUserExist(username);

    }

    public boolean authenticate(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = database.isLogin(username, password);
        if(success){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        }
        else{
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
        session.invalidate();
    }
}
