package ua.axiom.controller.commands.viewable;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.actors.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/adminpage")
public class AdminPageCommand extends Command<Admin> {
    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return getView();
    }

    protected String getView() {
        return "forvard:/appPages/adminpage.jsp";
    }
}
