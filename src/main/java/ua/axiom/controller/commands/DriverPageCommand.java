package ua.axiom.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverPageCommand extends Command {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/appPages/driverpage.jsp";
    }
}
