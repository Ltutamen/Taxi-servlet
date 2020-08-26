package ua.axiom.controller.commands.viewable.mainpage;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.impl.MultiTableRepository;
import ua.axiom.service.GuiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class GuestMainPageCommand extends Command<User> {
    private GuiService guiService;
    private MultiTableRepository<Long, User> test;

    {
        guiService = Context.get(GuiService.class);
        test = Context.get(MultiTableRepository.class);
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        nonUserSpecificDataFill(model, UserLocale.DEFAULT_LOCALE);

        for(Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return getView();
    }

    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale locale) {
        guiService.nonUserSpecificModelPopulation(model, UserLocale.DEFAULT_LOCALE);
    }

    protected String getView() {
        return "forward:/appPages/index/index.jsp";
    }

}
