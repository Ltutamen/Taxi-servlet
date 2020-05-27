package ua.axiom.controller.commands;

import ua.axiom.core.Context;
import ua.axiom.service.GuiService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageCommand extends Command {
    {
        guiService = Context.get(GuiService.class);
    }

    private GuiService guiService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
        guiService.setNavbarData(request);
        request.setAttribute("aa", "Company-name");

        return "forward:/appPages/index.jsp";

    }
}
