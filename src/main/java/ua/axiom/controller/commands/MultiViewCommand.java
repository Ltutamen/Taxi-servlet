package ua.axiom.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class MultiViewCommand extends Command {
    private final Map<Function<HttpServletRequest, Boolean>, Command> requestMapping;

    protected MultiViewCommand() {
        this.requestMapping = new HashMap<>();
    }

    protected void addCommand(Function<HttpServletRequest, Boolean> supplier, Command command) {
        requestMapping.put(supplier, command);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException, ServletException {
        Set<Command> commandToExecute = requestMapping
                .entrySet()
                .stream()
                .filter(es -> es.getKey().apply(request))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());

        assert commandToExecute.size() == 1;

        return commandToExecute.iterator().next().execute(request, response, uri);
    }
}
