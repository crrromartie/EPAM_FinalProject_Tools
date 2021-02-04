package by.gaponenko.tools.tag;

import by.gaponenko.tools.controller.command.AttributeName;
import by.gaponenko.tools.entity.Tool;
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
 * The tools pagination tag.
 * <p>
 * Custom tag. Processes a list of tools for displaying them,
 * adds a pagination view for more convenient viewing of the list.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ToolsPaginationTag extends TagSupport {
    static Logger logger = LogManager.getLogger();

    private static final int PAGE_ENTRIES = 6;
    private static final String CONTENT_PAGE = "/properties/pagecontent";
    private static final String TYPE = "tool_s_page.type";
    private static final String MODEL = "tool_s_page.model";
    private static final String RENT_PRICE = "tool_s_page.rent_price";
    private static final String AVAILABILITY = "tool_s_page.availability";
    private static final String PHOTO = "tool_s_page.photo";
    private static final String AVAILABLE = "tool_s_page.availability.available";
    private static final String NOT_AVAILABLE = "tool_s_page.availability.not_available";
    private static final String CHAINSAW = "tool_s_page.chainsaw";
    private static final String CONCRETE_SAW = "tool_s_page.concrete_saw";
    private static final String JIGSAW = "tool_s_page.jigsaw";
    private static final String PETROL_CUTTER = "tool_s_page.petrol_cutter";
    private static final String TILE_CUTTER = "tool_s_page.tile_cutter";
    private static final String BREAKER_HAMMER = "tool_s_page.breaker_hammer";
    private static final String SCREW = "tool_s_page.screw";
    private static final String DRILL = "tool_s_page.drill";
    private static final String PERFORATOR = "tool_s_page.perforator";
    private static final String WELDER = "tool_s_page.welder";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Locale lang = new Locale((String) session.getAttribute(AttributeName.LANG));
        User.Role role = (User.Role) session.getAttribute(AttributeName.ROLE);
        List<Tool> tools = (ArrayList<Tool>) session.getAttribute(AttributeName.TOOLS);
        int toolsPageNumber = (int) (session.getAttribute(AttributeName.TOOLS_PAGE_NUMBER));
        int beginIndex = toolsPageNumber * PAGE_ENTRIES - PAGE_ENTRIES;
        int endIndex = Math.min(toolsPageNumber * PAGE_ENTRIES, tools.size());
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, lang);
        JspWriter out = pageContext.getOut();
        if (tools.isEmpty()) {
            return SKIP_BODY;
        }
        try {
            out.write("<table class=\"table\"><thead>");
            out.write("<tr>");
            out.write("<th>" + bundle.getString(TYPE) + "</th>");
            out.write("<th>" + bundle.getString(MODEL) + "</th>");
            out.write("<th>" + bundle.getString(RENT_PRICE) + "</th>");
            out.write("<th>" + bundle.getString(AVAILABILITY) + "</th>");
            out.write("<th>" + bundle.getString(PHOTO) + "</th>");
            out.write("</tr>");
            out.write("</thead>");
            out.write("<tbody>");
            for (int i = beginIndex; i < endIndex; i++) {
                out.write("<tr>");
                String toolType = tools.get(i).getType().toString();
                switch (toolType) {
                    case "CHAINSAW" -> out.write("<td>" + bundle.getString(CHAINSAW) + "</td>");
                    case "CONCRETE_SAW" -> out.write("<td>" + bundle.getString(CONCRETE_SAW) + "</td>");
                    case "JIGSAW" -> out.write("<td>" + bundle.getString(JIGSAW) + "</td>");
                    case "PETROL_CUTTER" -> out.write("<td>" + bundle.getString(PETROL_CUTTER) + "</td>");
                    case "TILE_CUTTER" -> out.write("<td>" + bundle.getString(TILE_CUTTER) + "</td>");
                    case "BREAKER_HAMMER" -> out.write("<td>" + bundle.getString(BREAKER_HAMMER) + "</td>");
                    case "SCREW" -> out.write("<td>" + bundle.getString(SCREW) + "</td>");
                    case "DRILL" -> out.write("<td>" + bundle.getString(DRILL) + "</td>");
                    case "PERFORATOR" -> out.write("<td>" + bundle.getString(PERFORATOR) + "</td>");
                    case "WELDER" -> out.write("<td>" + bundle.getString(WELDER) + "</td>");
                    default -> logger.log(Level.ERROR, "Unexpected constant!");
                }
                if (role == User.Role.GUEST) {
                    out.write("<td><label>" + tools.get(i).getModel() + "</label></td>");
                }
                if (role == User.Role.CLIENT) {
                    if (tools.get(i).isAvailable()) {
                        out.write("<td><a class=\"link\" href=\"" + pageContext.getServletContext().getContextPath()
                                + "/ToolRental?command=tool_pass&toolId=" + tools.get(i).getToolId() + "\">"
                                + tools.get(i).getModel() + "</a></td>");
                    } else {
                        out.write("<td><label>" + tools.get(i).getModel() + "</label></td>");
                    }
                }
                if (role == User.Role.ADMIN) {
                    out.write("<td><a class=\"link\" href=\"" + pageContext.getServletContext().getContextPath()
                            + "/ToolRental?command=tool_edit_pass&toolId=" + tools.get(i).getToolId() + "\">"
                            + tools.get(i).getModel() + "</a></td>");
                }
                out.write("<td>" + tools.get(i).getRentPrice() + "</td>");
                if (tools.get(i).isAvailable()) {
                    out.write("<td>" + bundle.getString(AVAILABLE) + "</td>");
                } else {
                    out.write("<td>" + bundle.getString(NOT_AVAILABLE) + "</td>");
                }
                out.write("<td>" + "<img id=\"tool_photo_list\" alt=\"...\" " +
                        "src=\"data:image/jpeg;base64," + tools.get(i).getPhoto() + "\"/>" + "</td>");
                out.write("</tr>");
            }
            out.write("</tbody></table>");
            out.write("<div id=\"pagination_block\" align=\"center\"><tr><td>");
            if (beginIndex >= PAGE_ENTRIES) {
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
            logger.log(Level.ERROR, e.getMessage());
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
