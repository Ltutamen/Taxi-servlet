package ua.axiom.controller.commands.mainpage;

import ua.axiom.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestMainPageCommand extends Command {
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/appPages/index.jsp";
    }
}
