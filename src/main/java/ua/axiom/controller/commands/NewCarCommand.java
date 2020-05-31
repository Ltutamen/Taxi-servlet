package ua.axiom.controller.commands;

import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class NewCarCommand extends Command<Driver> {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {

        return "forward:/appPages/newCar.jsp";
    }

    @Override
    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale userLocale) {

    }

    @Override
    protected void userSpecificDataFill(Map<String, Object> model, Driver user) {

    }
}
