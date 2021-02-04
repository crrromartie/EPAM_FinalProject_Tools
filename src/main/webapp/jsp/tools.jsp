<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="tools_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row" id="tools-page">
            <div class="col-3">
                <form name="ToolsType" action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <input type="hidden" name="command" value="filter_tools"/>
                    <select class="custom-select" id="select" name="toolType">
                        <option selected value="ALL"><fmt:message key="tool_s_page.all"/></option>
                        <option value="CHAINSAW"><fmt:message key="tool_s_page.chainsaw"/></option>
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
                    <div class="form-group row">
                        <div class="col-4 offset-3">
                            <button type="submit" class="submit-button" id="butt">
                                <fmt:message key="tool_s_page.find"/>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="offset-3">
                    <label class="col-form-label"><fmt:message key="tool_s_page.sort_price"/></label></div>
                <div class="col-12 offset-1">
                    <div class="form-row form-group">
                        <form name="toolsSort" action="${pageContext.request.contextPath}/ToolRental" method="get">
                            <input type="hidden" name="command" value="sort_tools">
                            <input type="hidden" name="toolsSortCriteria" value="price_up">
                            <button type="submit" class="submit-button" id="but-sort-up">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-arrow-up-square" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm8.5 9.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z"/>
                                </svg>
                            </button>
                        </form>
                        <form name="toolsSort" action="${pageContext.request.contextPath}/ToolRental" method="get">
                            <input type="hidden" name="command" value="sort_tools">
                            <input type="hidden" name="toolsSortCriteria" value="price_down">
                            <button type="submit" class="submit-button" id="but-sort-down">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-arrow-down-square" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm8.5 2.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/>
                                </svg>
                            </button>
                        </form>
                    </div>
                </div>
                <div class="offset-3">
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <a class="link" href="${pageContext.request.contextPath}/ToolRental?command=tool_add_pass">
                            <fmt:message key="tool_s_page.add_new"/></a>
                    </c:if>
                </div>
            </div>
            <div class="col-9">
                <ctg:toolsPaginationTag/>
                <c:if test="${tools.size() == 0}">
                    <div class="message-empty">
                        <fmt:message key="tool_s_page.message"/>
                    </div>
                </c:if>
            </div>

        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
