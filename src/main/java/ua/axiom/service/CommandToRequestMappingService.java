package ua.axiom.service;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.CommandMappingService;
import ua.axiom.core.web.CommandProviderI;

import java.util.HashMap;


@CommandMappingService
public class CommandToRequestMappingService implements CommandProviderI {

    private final HashMap<String, Command<?>> uriToCommandMap = new HashMap<>();

    @Override
    public Command<?> getCommand(String path) {
        return uriToCommandMap.get(path);
    }

    @Override
    public void addCommand(String path, Command<?> command) {
        uriToCommandMap.put(path, command);
    }
}
