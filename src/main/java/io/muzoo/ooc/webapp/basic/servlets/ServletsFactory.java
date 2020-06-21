package io.muzoo.ooc.webapp.basic.servlets;

import java.util.ArrayList;
import java.util.List;

public class ServletsFactory {

    private static final List<AbstractRoutableHttpServlet> servletList = new ArrayList<>();

    static {
        servletList.add(new HomeServlet());
        servletList.add(new LoginServlet());
        servletList.add(new LogoutServlet());
        servletList.add(new RegisterServlet());
        servletList.add(new DisplayUserServlet());
        servletList.add(new EditServlet());
        servletList.add(new RemoveServlet());
    }

    public static List<AbstractRoutableHttpServlet> getServletList() {
        return servletList;
    }
}
