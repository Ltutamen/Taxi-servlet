package ua.axiom.core.web;

import ua.axiom.controller.Command;

/**
 * The implementation plob
 */
public interface CommandProviderI {
    void addCommand(String path, Command<?> command);

    Command<?> getCommand(String path);
}
