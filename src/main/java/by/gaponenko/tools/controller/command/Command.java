package by.gaponenko.tools.controller.command;

import by.gaponenko.tools.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}
