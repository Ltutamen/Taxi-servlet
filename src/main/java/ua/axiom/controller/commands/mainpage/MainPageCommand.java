package ua.axiom.controller.commands.mainpage;

import ua.axiom.controller.commands.MultiViewCommand;
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
        super.addCommand((request) -> request.getSession(false) == null || request.getSession(false).getAttribute("role").equals(Role.GUEST), new GuestMainPageCommand());
        super.addCommand((request) -> request.getSession(false) != null && !request.getSession(false).getAttribute("role").equals(Role.GUEST), new LoggedMainPageCommand());
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        guiService.setNavbarData(request);
        request.setAttribute("aa", "Company-name");

        return "forward:/appPages/index.jsp";
    }

}
