package ua.axiom.controller.commands.mainpage;

import ua.axiom.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggedMainPageCommand extends Command {
    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/appPages/loggedindex.jsp";
    }
}
