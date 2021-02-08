package by.gaponenko.tools.tag;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Users pagination tag.
 * <p>
 * Custom tag. Processes a list of clients for displaying them,
 * adds a pagination view for more convenient viewing of the list.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class UsersPaginationTag extends TagSupport {
    static Logger logger = LogManager.getLogger();

    private static final int PAGE_ENTRIES = 6;
    private static final String CONTENT_PAGE = "/properties/pagecontent";
    private static final String AVATAR = "clients_page.avatar";
    private static final String LOGIN = "clients_page.login";
    private static final String NAME_SURNAME = "clients_page.name_surname";
    private static final String EMAIL = "clients_page.email";
    private static final String PHONE = "clients_page.phone";
    private static final String CLIENT_STATUS = "clients_page.status";
    private static final String ACTION = "clients_page.action";
    private static final String ACTIVE = "clients_page.active";
    private static final String UNCONFIRMED = "clients_page.unconfirmed";
    private static final String BLOCKED = "clients_page.blocked";
    private static final String BLOCK = "clients_page.block";
    private static final String UNBLOCK = "clients_page.unblock";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Locale lang = new Locale((String) session.getAttribute(AttributeName.LANG));
        List<User> users = (ArrayList<User>) session.getAttribute(AttributeName.USERS);
        int usersPageNumber = (int) (session.getAttribute(AttributeName.USERS_PAGE_NUMBER));
        int beginIndex = usersPageNumber * PAGE_ENTRIES - PAGE_ENTRIES;
        int endIndex = Math.min(usersPageNumber * PAGE_ENTRIES, users.size());
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, lang);
        JspWriter out = pageContext.getOut();
        if (users.isEmpty()) {
            return SKIP_BODY;
        }
        try {
            out.write("<table class=\"table\"><thead>");
            out.write("<tr>");
            out.write("<th>" + bundle.getString(AVATAR) + "</th>");
            out.write("<th>" + bundle.getString(LOGIN) + "</th>");
            out.write("<th>" + bundle.getString(NAME_SURNAME) + "</th>");
            out.write("<th>" + bundle.getString(EMAIL) + "</th>");
            out.write("<th>" + bundle.getString(PHONE) + "</th>");
            out.write("<th>" + bundle.getString(CLIENT_STATUS) + "</th>");
            out.write("<th>" + bundle.getString(ACTION) + "</th>");
            out.write("</tr>");
            out.write("</thead>");
            out.write("<tbody>");
            for (int i = beginIndex; i < endIndex; i++) {
                out.write("<tr>");
                out.write("<td>" + "<img id=\"avatar_mini\" alt=\"...\" " +
                        "src=\"data:image/jpeg;base64," + users.get(i).getAvatar() + "\"/>" + "</td>");
                out.write("<td>" + users.get(i).getLogin() + "</td>");
                out.write("<td>" + users.get(i).getName() + " " + users.get(i).getSurname() + "</td>");
                out.write("<td>" + users.get(i).getEmail() + "</td>");
                out.write("<td>" + users.get(i).getPhone() + "</td>");
                String clientStatus = users.get(i).getStatus().toString();
                switch (clientStatus) {
                    case "ACTIVE" -> out.write("<td>" + bundle.getString(ACTIVE) + "</td>");
                    case "UNCONFIRMED" -> out.write("<td>" + bundle.getString(UNCONFIRMED) + "</td>");
                    case "BLOCKED" -> out.write("<td>" + bundle.getString(BLOCKED) + "</td>");
                    default -> logger.log(Level.ERROR, "Unexpected constant!");
                }
                out.write("<td>");
                if (users.get(i).getStatus() == User.Status.ACTIVE
                        || users.get(i).getStatus() == User.Status.UNCONFIRMED) {
                    out.write("<form action=\"" + pageContext.getServletContext().getContextPath()
                            + "/ToolRental\" method=\"get\">");
                    out.write("<input type=\"hidden\" name=\"command\" value=\"block_client\">");
                    out.write("<input type=\"hidden\" name=\"login\" value="
                            + users.get(i).getLogin() + ">");
                    out.write("<button type=\"submit\" class=\"submit-button\" id=\"blockUser\">");
                    out.write(bundle.getString(BLOCK));
                    out.write("</button>");
                    out.write("</form>");
                }
                if (users.get(i).getStatus() == User.Status.BLOCKED) {
                    out.write("<form action=\"" + pageContext.getServletContext().getContextPath()
                            + "/ToolRental\" method=\"get\">");
                    out.write("<input type=\"hidden\" name=\"command\" value=\"unblock_client\">");
                    out.write("<input type=\"hidden\" name=\"login\" value="
                            + users.get(i).getLogin() + ">");
                    out.write("<button type=\"submit\" class=\"submit-button\" id=\"unblockUser\">");
                    out.write(bundle.getString(UNBLOCK));
                    out.write("</button>");
                    out.write("</form>");
                }
                out.write("</td>");
                out.write("</tr>");
            }
            out.write("</tbody></table>");
            out.write("<div id=\"pagination_block\" align=\"center\"><tr><td>");
            if (beginIndex >= PAGE_ENTRIES) {
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
            logger.log(Level.ERROR, e.getMessage());
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
