package ua.axiom.core;

import ua.axiom.core.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextCreatorListener implements ServletContextListener {
    private final static String PACKAGE_TO_SCAN = "ua.axiom";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ApplicationContext.init();
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) { }
}
