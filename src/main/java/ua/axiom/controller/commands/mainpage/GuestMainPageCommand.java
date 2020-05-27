package ua.axiom.controller.commands.mainpage;

import ua.axiom.controller.commands.Command;
import ua.axiom.core.Context;
import ua.axiom.service.GuiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestMainPageCommand extends Command {
    private GuiService guiService;

    {
        guiService = Context.get(GuiService.class);
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        guiService.setNavbarData(request);

        request.setAttribute("aa", "Company-name");

        return "forward:/appPages/index.jsp";
    }
}
