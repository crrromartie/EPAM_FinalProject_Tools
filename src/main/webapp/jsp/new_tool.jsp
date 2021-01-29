<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 24.01.2021
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="tool_page.new_tool.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="AddTool" class="form-horizontal" action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <input type="hidden" name="command" value="add_tool"/>
                    <span class="heading"><fmt:message key="tool_s_page.add_new"/></span>
                    <div class="form-group">
                        <span><fmt:message key="tool_s_page.shoos_type"/></span>
                        <select class="custom-select" id="select" name="toolType" required>
                            <option selected value="CHAINSAW"><fmt:message key="tool_s_page.chainsaw"/></option>
                            <option value="CONCRETE_SAW"><fmt:message key="tool_s_page.concrete_saw"/></option>
                            <option value="JIGSAW"><fmt:message key="tool_s_page.jigsaw"/></option>
                            <option value="PETROL_CUTTER"><fmt:message key="tool_s_page.petrol_cutter"/></option>
                            <option value="TILE_CUTTER"><fmt:message key="tool_s_page.tile_cutter"/></option>
                            <option value="BREAKER_HAMMER"><fmt:message key="tool_s_page.breaker_hammer"/></option>
                            <option value="SCREW"><fmt:message key="tool_s_page.screw"/></option>
                            <option value="DRILL"><fmt:message key="tool_s_page.drill"/></option>
                            <option value="PERFORATOR"><fmt:message key="tool_s_page.perforator"/></option>
                            <option value="WELDER"><fmt:message key="tool_s_page.welder"/></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="model"
                               title="<fmt:message key="tool_s_page.model"/>"
                               pattern=".{1,50}"
                               required
                               placeholder="<fmt:message key="tool_s_page.model"/>"
                               value="${toolParameters.get("model")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.model_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <textarea type="text" class="form-control" name="descriptionRus"
                                  title="<fmt:message key="tool_s_page.description_rus"/>"
                                  pattern=".{1,250}"
                                  required
                                  spellcheck="false"
                                  placeholder="<fmt:message key="tool_s_page.description_rus"/>"
                                  value="${toolParameters.get("descriptionRus")}"
                                  oninvalid="this.setCustomValidity('<fmt:message
                                          key="oninvalid.description"/>')"
                                  onchange="this.setCustomValidity('')"></textarea>
                    </div>
                    <div class="form-group">
                        <textarea type="text" class="form-control" name="descriptionEng"
                                  title="<fmt:message key="tool_s_page.description_eng"/>"
                                  pattern=".{1,250}"
                                  required
                                  spellcheck="false"
                                  placeholder="<fmt:message key="tool_s_page.description_eng"/>"
                                  value="${toolParameters.get("descriptionEng")}"
                                  oninvalid="this.setCustomValidity('<fmt:message
                                          key="oninvalid.description"/>')"
                                  onchange="this.setCustomValidity('')"></textarea>
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" name="rentPrice"
                               title="<fmt:message key="tool_s_page.rent_price"/>"
                               min="1"
                               max="99"
                               required
                               placeholder="<fmt:message key="tool_s_page.rent_price"/>"
                               value="${toolParameters.get("rentPrice")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.rent_price"/>')"
                               onchange="this.setCustomValidity('')"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="tool_s_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${uniqueModelError eq true}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="tool_s_page.message.model_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${toolIncorrectData eq true}">
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
