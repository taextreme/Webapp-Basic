package io.muzoo.ooc.webapp.basic.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplayUserServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorize(request)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/src/main/WEB-INF/home.jsp");
            requestDispatcher.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }


    @Override
    public String getPattern() {
        return "/users";
    }
}
