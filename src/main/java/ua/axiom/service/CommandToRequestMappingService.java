package ua.axiom.service;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.CommandMappingService;
import ua.axiom.core.web.CommandProviderI;

import java.util.HashMap;


@CommandMappingService
public class CommandToRequestMappingService implements CommandProviderI {

    private final HashMap<String, Command<?>> uriToCommandMap = new HashMap<>();

    public CommandToRequestMappingService() {
        /*uriToCommand.put("/clientpage", new ClientPageCommand());
        uriToCommand.put("/driverpage", new DriverPageCommand());
        uriToCommand.put("/adminpage", new AdminPageCommand());

        uriToCommand.put("/neworder", new NewOrderCommand());
        uriToCommand.put("/api/neworder", new NewOrderPostCommand());

        uriToCommand.put("/api/postloginredirect", new PostLoginCommand());

        uriToCommand.put("/login", new LoginPageCommand());
        uriToCommand.put("/logout", new LogoutCommand());
        uriToCommand.put("/error", new ErrorCommand());
        uriToCommand.put("/", new MainPageCommand());
        uriToCommand.put("/index", new MainPageCommand());

        uriToCommand.put("/driverpage/takeorder", new DriverPageTakeOrder());
        uriToCommand.put("/driverpage/confirmation", new DriverPageOrderConfirmation());
        uriToCommand.put("/clientpage/confirm", new ClientPageOrderConfirmation());
        uriToCommand.put("/clientpage/cancelorder", new ClientPageCancelOrder());*/
    }

    @Override
    public Command<?> getCommand(String path) {
        return uriToCommandMap.get(path);
    }

    @Override
    public void addCommand(String path, Command<?> command) {
        uriToCommandMap.put(path, command);
    }
}
