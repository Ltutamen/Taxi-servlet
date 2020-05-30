package ua.axiom.controller.commands.mainpage;

import ua.axiom.controller.commands.MultiViewCommand;
import ua.axiom.model.Role;
import ua.axiom.service.SessionContextService;

public class MainPageCommand extends MultiViewCommand {
    public MainPageCommand() {
        super.addCommand((request) -> request.getSession(false) == null || SessionContextService.getCurrentUserRole(request.getSession()).equals(Role.GUEST), new GuestMainPageCommand());
        super.addCommand((request) -> request.getSession(false) != null && !SessionContextService.getCurrentUserRole(request.getSession()).equals(Role.GUEST), new LoggedMainPageCommand());
    }
}
