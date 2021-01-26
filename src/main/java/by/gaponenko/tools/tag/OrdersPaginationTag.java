package by.gaponenko.tools.tag;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.Order;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class OrdersPaginationTag extends TagSupport {
    private int ordersPageNumber;
    private int pageEntries;

    public void setOrdersPageNumber(int ordersPageNumber) {
        this.ordersPageNumber = ordersPageNumber;
    }

    public void setPageEntries(int pageEntries) {
        this.pageEntries = pageEntries;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        List<Order> orders = (List<Order>) session.getAttribute(AttributeName.ORDERS);
        JspWriter out = pageContext.getOut();
        try {
            int beginIndex = ordersPageNumber * pageEntries - pageEntries;
            int endIndex = Math.min(ordersPageNumber * pageEntries, orders.size());
            out.write("<div id=\"pagination_block\" align=\"center\"><tr><td>");
            if (beginIndex >= pageEntries) {
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
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
