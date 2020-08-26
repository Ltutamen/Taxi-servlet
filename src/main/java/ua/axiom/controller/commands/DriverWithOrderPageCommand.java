package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.model.actors.Driver;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;
import ua.axiom.service.buisness.CarService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverWithOrderPageCommand extends Command<Driver> {
    private LocalisationService localisationService;
    private GuiService guiService;
    private OrderService orderService;
    private CarService carService;



    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return super.processGet(request, response);
    }
}
