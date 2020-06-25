package io.muzoo.ooc.webapp.basic.servlets;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends AbstractRoutableHttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(securityService.isAuthorize(request)){
            String username = securityService.getCurrentrUsername(request);
            String name = securityService.getName(username);
            request.setAttribute("name", name);
            request.setAttribute("username", username);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
            requestDispatcher.include(request, response);
        }
        else{
            response.sendRedirect("/login");
        }

    }

    @Override
    public String getPattern() {
        return "/index.jsp";
    }
}
