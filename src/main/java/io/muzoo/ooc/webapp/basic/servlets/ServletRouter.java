package io.muzoo.ooc.webapp.basic.servlets;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletRouter {

    private final List<Class<? extends AbstractRoutableHttpServlet>> servletClasses = new ArrayList<>();

    {
        servletClasses.add(HomeServlet.class);
        servletClasses.add(LoginServlet.class);
        servletClasses.add(LogoutServlet.class);
    }

    public void inti(Context ctx){

        for(Class<? extends AbstractRoutableHttpServlet> servletClass : servletClasses){
            try{
                AbstractRoutableHttpServlet httpServlet = servletClass.newInstance();
                Tomcat.addServlet(ctx, servletClass.getSimpleName(), httpServlet);
                ctx.addServletMappingDecoded(httpServlet.getPattern(), servletClass.getSimpleName());
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }

        }

    }
}
