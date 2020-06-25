package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/register.jsp");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        if (securityService.addNewUser(username, password, name)) {
            String message = "Successfully register a new user.";
            request.setAttribute("message", message);
            response.sendRedirect("/");
        } else {
            String message = "An error occurred in registering a new user.";
            request.setAttribute("message", message);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/register.jsp");
        requestDispatcher.include(request, response);
    }


    @Override
    public String getPattern() {
        return "/register";
    }
}
