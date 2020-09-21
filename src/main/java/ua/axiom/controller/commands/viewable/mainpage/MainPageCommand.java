package ua.axiom.controller.commands.viewable.mainpage;

import ua.axiom.controller.commands.MultiViewCommand;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.Role;
import ua.axiom.model.actors.User;
import ua.axiom.service.SessionContextService;

@RequestMapping("/")
public class MainPageCommand extends MultiViewCommand<User> {
    @Autowired
    private GuestMainPageCommand guestMainPageCommand;

    @Autowired
    private LoggedMainPageCommand loggedMainPageCommand;

    public MainPageCommand() {
    }

    @InitMethod
    private void init() {
        super.addCommand((request) -> request.getSession(false) == null || SessionContextService.getCurrentUserRole(request.getSession()).equals(Role.GUEST), guestMainPageCommand);
        super.addCommand((request) -> request.getSession(false) != null && !SessionContextService.getCurrentUserRole(request.getSession()).equals(Role.GUEST), loggedMainPageCommand);
    }
}
