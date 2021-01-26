package by.gaponenko.tools.tag;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.Tool;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ToolsPaginationTag extends TagSupport {
    private int toolsPageNumber;
    private int pageEntries;

    public void setToolsPageNumber(int toolsPageNumber) {
        this.toolsPageNumber = toolsPageNumber;
    }

    public void setPageEntries(int pageEntries) {
        this.pageEntries = pageEntries;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        List<Tool> tools = (List<Tool>) session.getAttribute(AttributeName.TOOLS);
        JspWriter out = pageContext.getOut();
        try {
            int beginIndex = toolsPageNumber * pageEntries - pageEntries;
            int endIndex = Math.min(toolsPageNumber * pageEntries, tools.size());
            out.write("<div id=\"pagination_block\" align=\"center\"><tr><td>");
            if (beginIndex >= pageEntries) {
                out.write("<a href=\"" + pageContext.getServletContext().getContextPath() +
                        "/ToolRental?command=pagination&paginationSubject=pagination_tools" +
                        "&paginationDirection=previousPage\">&lt</a>");
            }
            out.write("</td>");
            out.write("<label>" + toolsPageNumber + "</label>");
            out.write("<td>");
            if (endIndex < tools.size()) {
                out.write("<a href=\"" + pageContext.getServletContext().getContextPath() +
                        "/ToolRental?command=pagination&paginationSubject=pagination_tools" +
                        "&paginationDirection=nextPage\">&gt</a>");
            }
            out.write("</div></td></tr>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
