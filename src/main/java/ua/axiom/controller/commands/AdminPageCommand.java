package ua.axiom.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageCommand extends Command {
    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return getView();
    }

    protected String getView() {
        return "forvard:/appPages/adminpage.jsp";
    }
}
