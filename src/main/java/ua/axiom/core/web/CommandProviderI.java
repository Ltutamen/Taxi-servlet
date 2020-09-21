package ua.axiom.core.web;

import ua.axiom.controller.Command;

public interface CommandProviderI {
    void addCommand(String path, Command<?> command);

    Command<?> getCommand(String path);
}
