package io.muzoo.ooc.webapp.basic.servlets;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class ServletRouter {

    public void inti(Context ctx){
        for (AbstractRoutableHttpServlet servlet : ServletsFactory.getServletList()) {
            try {
                String name = servlet.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, servlet);
                ctx.addServletMappingDecoded(servlet.getPattern(), name);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
