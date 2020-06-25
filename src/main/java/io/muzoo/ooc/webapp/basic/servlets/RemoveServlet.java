package io.muzoo.ooc.webapp.basic.servlets;

import io.muzoo.ooc.webapp.basic.security.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id-to-remove"));
        int sessionId = (Integer) request.getSession().getAttribute("sessionId");
        if (id != sessionId) {
            if (SecurityService.getInstance().removeUser(id)) {
                String message = "Successfully removed a user.";
                request.setAttribute("message", message);
            } else {
                String message = "An error occurred in removing user.";
                request.setAttribute("message", message);
            }
        } else {
            String message = "You cannot remove yourself.";
            request.setAttribute("message", message);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
        requestDispatcher.include(request, response);
    }

    @Override
    public String getPattern() {
        return "/removeuser";
    }
}
