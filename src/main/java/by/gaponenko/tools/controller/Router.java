package by.gaponenko.tools.controller;

import by.gaponenko.tools.controller.command.PagePath;

public final class Router {

    enum Type {
        FORWARD,
        REDIRECT
    }

    private Type type = Type.FORWARD;
    private String page = PagePath.INDEX_PAGE;

    public Router() {
    }

    public Router(String currentPage) {
        this.page = currentPage;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public Type getType() {
        return type;
    }
}
