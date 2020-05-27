package ua.axiom.controller.commands;

import ua.axiom.controller.commands.mainpage.*;
import ua.axiom.core.Context;
import ua.axiom.model.Role;
import ua.axiom.service.GuiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand extends MultiViewCommand {
    {
        guiService = Context.get(GuiService.class);
    }

    private GuiService guiService;

    public MainPageCommand() {
        super.addCommand((request) -> request.getSession(false) == null || request.getSession(false).getAttribute("role").equals(Role.GUEST), Context.get(GuestMainPageCommand.class));
        super.addCommand((request) -> request.getSession(false) != null && request.getSession(false).getAttribute("role").equals(Role.GUEST), Context.get(LoggedMainPageCommand.class));
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        guiService.setNavbarData(request);
        request.setAttribute("aa", "Company-name");

        return "forward:/appPages/index.jsp";
    }

}
