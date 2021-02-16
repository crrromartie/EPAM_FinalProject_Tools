<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="tool_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6">
                <div class="card" style="width: 100%;">
                    <img src="data:image/jpeg;base64,${tool.getPhoto()}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:choose>
                                <c:when test="${tool.getType() eq 'CHAINSAW'}">
                                    <fmt:message key="tool_s_page.chainsaw"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'CONCRETE_SAW'}">
                                    <fmt:message key="tool_s_page.concrete_saw"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'JIGSAW'}">
                                    <fmt:message key="tool_s_page.jigsaw"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'PETROL_CUTTER'}">
                                    <fmt:message key="tool_s_page.petrol_cutter"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'TILE_CUTTER'}">
                                    <fmt:message key="tool_s_page.tile_cutter"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'BREAKER_HAMMER'}">
                                    <fmt:message key="tool_s_page.breaker_hammer"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'SCREW'}">
                                    <fmt:message key="tool_s_page.screw"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'DRILL'}">
                                    <fmt:message key="tool_s_page.drill"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'PERFORATOR'}">
                                    <fmt:message key="tool_s_page.perforator"/>
                                </c:when>
                                <c:when test="${tool.getType() eq 'WELDER'}">
                                    <fmt:message key="tool_s_page.welder"/>
                                </c:when>
                            </c:choose>
                        </h5>
                        <h5><c:out value="${tool.getModel()}"/></h5>
                        <h5><c:out value="${tool.getRentPrice()}"/></h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><c:out value="${tool.getDescriptionRus()}"/></li>
                        <li class="list-group-item"><c:out value="${tool.getDescriptionEng()}"/></li>
                    </ul>
                </div>
            </div>
            <div class="col-6">
                <form name="SelectTool" action="${pageContext.request.contextPath}/ToolRental"
                      method="post" class="tool_select_form">
                    <input type="hidden" name="command" value="calculate_order_total">
                    <div class="form-row form-group">
                        <div class="col-4 label-column">
                            <label class="col-form-label"><fmt:message key="tool_s_page.order_day"/></label>
                        </div>
                        <div class="col-7 input-column">
                            <input class="form-control form-control--date" type="date" name="order_date"
                                   required
                                   oninvalid="this.setCustomValidity('<fmt:message key="oninvalid.order_date"/>')"
                                   onchange="this.setCustomValidity('')"/>
                        </div>
                    </div>
                    <div class="form-row form-group">
                        <div class="col-4 label-column">
                            <label class="col-form-label"><fmt:message key="tool_s_page.return_day"/></label>
                        </div>
                        <div class="col-7 input-column">
                            <input class="form-control form-control--date" type="date" name="return_date"
                                   required
                                   max="2021-12-31"
                                   oninvalid="this.setCustomValidity('<fmt:message key="oninvalid.return_day"/>')"
                                   onchange="this.setCustomValidity('')"/>
                        </div>
                    </div>
                    <div class="form-row form-group">
                        <div class="col-4 offset-4">
                            <button class="submit-button" type="submit" id="but-order">
                                <fmt:message key="tool_s_page.order"/>
                            </button>
                        </div>
                    </div>
                </form>
                <c:if test="${totalCost > 0}">
                    <form name="SubmitTool" action="${pageContext.request.contextPath}/ToolRental"
                          method="post" class="tool_submit_form">
                        <input type="hidden" name="command" value="make_order">
                        <input type="hidden" name="totalCost" value="${totalCost}">
                        <div class="form-row form-group">
                            <label class="col-form-label">
                                <fmt:message key="tool_s_page.total"/><c:out value="${totalCost}"/>
                            </label>
                        </div>
                        <div class="form-row form-group">
                            <div>
                                <button class="submit-button" type="submit" id="but-submit">
                                    <fmt:message key="tool_s_page.submit"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/data_range.js"></script>
</body>
</html>
