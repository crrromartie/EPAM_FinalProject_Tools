package by.gaponenko.tools.tag;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.Order;
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
 * The Orders pagination tag.
 * <p>
 * Custom tag. Processes a list of orders for displaying them,
 * adds a pagination view for more convenient viewing of the list.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class OrdersPaginationTag extends TagSupport {
    static Logger logger = LogManager.getLogger();

    private static final int PAGE_ENTRIES = 6;
    private static final String CONTENT_PAGE = "/properties/pagecontent";
    private static final String LOGIN = "orders_page.login";
    private static final String NAME_SURNAME = "orders_page.name_surname";
    private static final String EMAIL = "orders_page.email";
    private static final String MODEL = "orders_page.tool_model";
    private static final String ORDER_DATE = "orders_page.order_date";
    private static final String RETURN_DATE = "orders_page.return_date";
    private static final String TOTAL_COST = "orders_page.total_cost";
    private static final String STATUS = "orders_page.status";
    private static final String ACTION = "orders_page.action";
    private static final String NEW = "orders_page.new";
    private static final String APPROVED = "orders_page.approved";
    private static final String CANCELED = "orders_page.canceled";
    private static final String REJECTED = "orders_page.rejected";
    private static final String ACTIVE = "orders_page.active";
    private static final String COMPLETE = "orders_page.complete";
    private static final String APPROVE = "orders_page.approve";
    private static final String REJECT = "orders_page.reject";
    private static final String CANCEL = "orders_page.cancel";
    private static final String PAY = "orders_page.pay";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Locale lang = new Locale((String) session.getAttribute(AttributeName.LANG));
        User.Role role = (User.Role) session.getAttribute(AttributeName.ROLE);
        List<Order> orders = (ArrayList<Order>) session.getAttribute(AttributeName.ORDERS);
        int ordersPageNumber = (int) (session.getAttribute(AttributeName.ORDERS_PAGE_NUMBER));
        int beginIndex = ordersPageNumber * PAGE_ENTRIES - PAGE_ENTRIES;
        int endIndex = Math.min(ordersPageNumber * PAGE_ENTRIES, orders.size());
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, lang);
        JspWriter out = pageContext.getOut();
        if (orders.isEmpty()) {
            return SKIP_BODY;
        }
        try {
            out.write("<table class=\"table\"><thead>");
            out.write("<tr>");
            if (role == User.Role.ADMIN) {
                out.write("<th>" + bundle.getString(LOGIN) + "</th>");
                out.write("<th>" + bundle.getString(NAME_SURNAME) + "</th>");
                out.write("<th>" + bundle.getString(EMAIL) + "</th>");
            }
            out.write("<th>" + bundle.getString(MODEL) + "</th>");
            out.write("<th>" + bundle.getString(ORDER_DATE) + "</th>");
            out.write("<th>" + bundle.getString(RETURN_DATE) + "</th>");
            out.write("<th>" + bundle.getString(TOTAL_COST) + "</th>");
            out.write("<th>" + bundle.getString(STATUS) + "</th>");
            out.write("<th>" + bundle.getString(ACTION) + "</th>");
            out.write("</tr>");
            out.write("</thead>");
            out.write("<tbody>");
            for (int i = beginIndex; i < endIndex; i++) {
                out.write("<tr>");
                if (role == User.Role.ADMIN) {
                    out.write("<td>" + orders.get(i).getUser().getLogin() + "</td>");
                    out.write("<td>" + orders.get(i).getUser().getName() + " "
                            + orders.get(i).getUser().getSurname() + "</td>");
                    out.write("<td>" + orders.get(i).getUser().getEmail() + "</td>");
                }
                out.write("<td>" + orders.get(i).getTool().getModel() + "</td>");
                out.write("<td>" + orders.get(i).getOrderDate() + "</td>");
                out.write("<td>" + orders.get(i).getReturnDate() + "</td>");
                out.write("<td>" + orders.get(i).getTotalCost() + "</td>");
                String status = orders.get(i).getStatus().toString();
                switch (status) {
                    case "NEW" -> out.write("<td>" + bundle.getString(NEW) + "</td>");
                    case "APPROVED" -> out.write("<td>" + bundle.getString(APPROVED) + "</td>");
                    case "CANCELED" -> out.write("<td>" + bundle.getString(CANCELED) + "</td>");
                    case "REJECTED" -> out.write("<td>" + bundle.getString(REJECTED) + "</td>");
                    case "ACTIVE" -> out.write("<td>" + bundle.getString(ACTIVE) + "</td>");
                    case "COMPLETE" -> out.write("<td>" + bundle.getString(COMPLETE) + "</td>");
                    default -> logger.log(Level.ERROR, "Unexpected constant!");
                }
                if (role == User.Role.ADMIN) {
                    out.write("<td>");
                    if (orders.get(i).getStatus() == Order.Status.NEW) {
                        out.write("<form action=\"" + pageContext.getServletContext().getContextPath()
                                + "/ToolRental\" method=\"get\">");
                        out.write("<input type=\"hidden\" name=\"command\" value=\"approve_order\">");
                        out.write("<input type=\"hidden\" name=\"orderId\" value="
                                + orders.get(i).getOrderId() + ">");
                        out.write("<button type=\"submit\" class=\"submit-button\" id=\"approveOrder\">");
                        out.write(bundle.getString(APPROVE));
                        out.write("</button>");
                        out.write("</form>");
                    }
                    if (orders.get(i).getStatus() == Order.Status.NEW
                            || orders.get(i).getStatus() == Order.Status.APPROVED) {
                        out.write("<form action=\"" + pageContext.getServletContext().getContextPath()
                                + "/ToolRental\" method=\"get\">");
                        out.write("<input type=\"hidden\" name=\"command\" value=\"reject_order\">");
                        out.write("<input type=\"hidden\" name=\"orderId\" value="
                                + orders.get(i).getOrderId() + ">");
                        out.write("<button type=\"submit\" class=\"submit-button\" id=\"rejectOrder\">");
                        out.write(bundle.getString(REJECT));
                        out.write("</button>");
                        out.write("</form>");
                    }
                    out.write("</td>");
                }
                if (role == User.Role.CLIENT) {
                    out.write("<td>");
                    if (orders.get(i).getStatus() == Order.Status.NEW
                            || orders.get(i).getStatus() == Order.Status.APPROVED) {
                        out.write("<form action=\"" + pageContext.getServletContext().getContextPath()
                                + "/ToolRental\" method=\"get\">");
                        out.write("<input type=\"hidden\" name=\"command\" value=\"cancel_order\">");
                        out.write("<input type=\"hidden\" name=\"orderId\" value="
                                + orders.get(i).getOrderId() + ">");
                        out.write("<button type=\"submit\" class=\"submit-button\" id=\"cancelOrder\">");
                        out.write(bundle.getString(CANCEL));
                        out.write("</button>");
                        out.write("</form>");
                    }
                    if (orders.get(i).getStatus() == Order.Status.APPROVED) {
                        out.write("<form action=\"" + pageContext.getServletContext().getContextPath()
                                + "/ToolRental\" method=\"get\">");
                        out.write("<input type=\"hidden\" name=\"command\" value=\"payment_pass\">");
                        out.write("<input type=\"hidden\" name=\"orderId\" value="
                                + orders.get(i).getOrderId() + ">");
                        out.write("<button type=\"submit\" class=\"submit-button\" id=\"payPass\">");
                        out.write(bundle.getString(PAY));
                        out.write("</button>");
                        out.write("</form>");
                    }
                    out.write("</td>");
                }
                out.write("</tr>");
            }
            out.write("</tbody></table>");
            out.write("<div id=\"pagination_block\" align=\"center\"><tr><td>");
            if (beginIndex >= PAGE_ENTRIES) {
                out.write("<a href=\"" + pageContext.getServletContext().getContextPath() +
                        "/ToolRental?command=pagination&paginationSubject=pagination_orders" +
                        "&paginationDirection=previousPage\">&lt</a>");
            }
            out.write("</td>");
            out.write("<label>" + ordersPageNumber + "</label>");
            out.write("<td>");
            if (endIndex < orders.size()) {
                out.write("<a href=\"" + pageContext.getServletContext().getContextPath() +
                        "/ToolRental?command=pagination&paginationSubject=pagination_orders" +
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
