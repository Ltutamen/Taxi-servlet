package ua.axiom.controller.commands;

import ua.axiom.core.Context;
import ua.axiom.service.GuiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand extends Command {
    {
        guiService = Context.get(GuiService.class);
    }

    private GuiService guiService;

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        guiService.setNavbarData(request);
        request.setAttribute("aa", "Company-name");

        return "forward:/appPages/index.jsp";
    }

}
