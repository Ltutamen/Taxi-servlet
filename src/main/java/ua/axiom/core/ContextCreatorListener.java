package ua.axiom.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextCreatorListener implements ServletContextListener {
    private final static String PACKAGE_TO_SCAN = "ua.axiom";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) { }
}
