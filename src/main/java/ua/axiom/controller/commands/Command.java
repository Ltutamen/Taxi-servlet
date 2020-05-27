package ua.axiom.controller.commands;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri)
            throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            return executePost(request, response);
        } else if(request.getMethod().equals("GET")) {
            return executeGet(request, response);
        } else {
            throw new IllegalStateException("Wrong request method:" + request.getMethod());
        }

    }

    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        throw new NotImplementedException();
    }

    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        throw new NotImplementedException();
    }

}
