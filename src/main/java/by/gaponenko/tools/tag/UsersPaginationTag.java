package by.gaponenko.tools.tag;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersPaginationTag extends TagSupport {
    private int usersPageNumber;
    private int pageEntries;

    public void setUsersPageNumber(int usersPageNumber) {
        this.usersPageNumber = usersPageNumber;
    }

    public void setPageEntries(int pageEntries) {
        this.pageEntries = pageEntries;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        List<User> users = (ArrayList<User>) session.getAttribute(AttributeName.USERS);
        JspWriter out = pageContext.getOut();
        try {
            int beginIndex = usersPageNumber * pageEntries - pageEntries;
            int endIndex = Math.min(usersPageNumber * pageEntries, users.size());
            out.write("<div id=\"pagination_block\" align=\"center\"><tr><td>");
            if (beginIndex >= pageEntries) {
                out.write("<a href=\"" + pageContext.getServletContext().getContextPath() +
                        "/ToolRental?command=pagination&paginationSubject=pagination_users" +
                        "&paginationDirection=previousPage\">&lt</a>");
            }
            out.write("</td>");
            out.write("<label>" + usersPageNumber + "</label>");
            out.write("<td>");
            if (endIndex < users.size()) {
                out.write("<a href=\"" + pageContext.getServletContext().getContextPath() +
                        "/ToolRental?command=pagination&paginationSubject=pagination_users" +
                        "&paginationDirection=nextPage\">&gt</a>");
            }
            out.write("</div></td></tr>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
