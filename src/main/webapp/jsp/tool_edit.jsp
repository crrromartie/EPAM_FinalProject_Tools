<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 26.01.2021
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="tool_edit_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="EditTool" class="form-horizontal" action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <input type="hidden" name="command" value="edit_tool"/>
                    <span class="heading"><fmt:message key="tool_s_page.edit_tool"/></span>
                    <div class="form-group">
                        <img id="tool_photo" src="data:image/jpeg;base64,${tool.getPhoto()}" alt="..."/>
                    </div>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/ToolRental?command=tool_photo_pass&toolId=${tool.getToolId()}">
                            <fmt:message key="tool_s_page.update_photo"/></a>
                    </div>
                    <h5>${tool.getModel()}</h5>
                    <div class="form-group">
                        <span><fmt:message key="tool_s_page.description_rus"/></span>
                        <textarea type="text" class="form-control" name="descriptionRus"
                                  title="<fmt:message key="tool_s_page.description_rus"/>"
                                  pattern=".{1,250}"
                                  spellcheck="false"
                                  required
                                  placeholder="<fmt:message key="tool_s_page.description_rus"/>"
                                  oninvalid="this.setCustomValidity('<fmt:message
                                          key="oninvalid.description"/>')"
                                  onchange="this.setCustomValidity('')">${tool.getDescriptionRus()}</textarea>
                    </div>
                    <div class="form-group">
                        <span><fmt:message key="tool_s_page.description_eng"/></span>
                        <textarea type="text" class="form-control" name="descriptionEng"
                                  title="<fmt:message key="tool_s_page.description_eng"/>"
                                  pattern=".{1,250}"
                                  spellcheck="false"
                                  required
                                  placeholder="<fmt:message key="tool_s_page.description_eng"/>"
                                  oninvalid="this.setCustomValidity('<fmt:message
                                          key="oninvalid.description"/>')"
                                  onchange="this.setCustomValidity('')">${tool.getDescriptionEng()}</textarea>
                    </div>
                    <div class="form-group">
                        <span><fmt:message key="tool_s_page.rent_price"/></span>
                        <input type="number" class="form-control" name="rentPrice"
                               title="<fmt:message key="tool_s_page.rent_price"/>"
                               min="1"
                               max="99"
                               required
                               placeholder="<fmt:message key="tool_s_page.rent_price"/>"
                               value="${tool.getRentPrice()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.rent_price"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <span><fmt:message key="tool_s_page.availability"/></span>
                        <select class="custom-select" id="select" name="availability">
                            <option selected value="${tool.isAvailable()}"><fmt:message
                                    key="tool_s_page.no_changes"/></option>
                            <option value="true"><fmt:message key="tool_s_page.availability.available"/></option>
                            <option value="false"><fmt:message key="tool_s_page.availability.not_available"/></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="tool_s_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${toolEditIncorrectData eq true}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="tool_s_page.message.incorrect_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
