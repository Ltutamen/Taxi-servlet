package ua.axiom.controller.commands.viewable.mainpage;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.User;
import ua.axiom.service.GuiService;
import ua.axiom.service.SessionContextService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class LoggedMainPageCommand extends Command<User> {
    @Autowired
    private GuiService guiService;

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        userSpecificDataFill(model, SessionContextService.getPersistedCurrentUser(request.getSession()));

        return "forward:/appPages/index/loggedindex.jsp";
    }

    protected void userSpecificDataFill(Map<String, Object> model, User user) {
        guiService.userSpecificModelPopulation(model, user);
    }
}
