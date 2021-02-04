package by.gaponenko.tools.controller;

import by.gaponenko.tools.controller.command.PagePath;

/**
 * The Router.
 * <p>
 * Used to store the path to the page to which the controller will redirect.
 * In addition to the path, the router contains information about the type
 * of transition redirect or forward.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public final class Router {
    /**
     * The enum Type.
     */
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
