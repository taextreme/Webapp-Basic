package io.muzoo.ooc.webapp.basic;

import io.muzoo.ooc.webapp.basic.servlets.ServletRouter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Webapp {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);

        File doceBase = new File("src/main/webapp/");
        doceBase.mkdirs();

        ServletRouter servletRouter = new ServletRouter();
        try {
            Context ctx = tomcat.addWebapp("", doceBase.getAbsolutePath());
            servletRouter.inti(ctx);
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
