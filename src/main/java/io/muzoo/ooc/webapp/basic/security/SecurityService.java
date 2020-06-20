package io.muzoo.ooc.webapp.basic.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class SecurityService {

    private UserService userService;

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

    public boolean login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findByUsername(username);
        if(user!=null && Objects.equals(user.getPassword(), password)){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        }
        else{
            return false;
        }
    }

    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
    }
}
