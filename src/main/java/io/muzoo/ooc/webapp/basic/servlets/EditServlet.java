package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends AbstractRoutableHttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id-edit"));
        String username = request.getParameter("username-edit");
        String name = request.getParameter("name-edit");
        String password = request.getParameter("password-edit");
        if (SecurityService.getInstance().editUser(id, username, password, name)) {
            String message = "Successfully edited data of " + name + ".";
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            rd.include(request, response);
        } else {
            String message = "An error occurred in editing user " + name + ".";
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            rd.include(request, response);
        }
    }

    @Override
    public String getPattern() {
        return "/edituser";
    }
}
