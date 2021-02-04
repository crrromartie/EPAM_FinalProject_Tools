<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="orders_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-3">
                <form name="OrdersType" action="${pageContext.request.contextPath}/ToolRental" method="post">
                    <input type="hidden" name="command" value="filter_orders"/>
                    <select class="custom-select" id="select" name="orderStatus">
                        <option selected value="ALL"><fmt:message key="orders_page.all"/></option>
                        <option value="NEW"><fmt:message key="orders_page.new"/></option>
                        <option value="APPROVED"><fmt:message key="orders_page.approved"/></option>
                        <option value="CANCELED"><fmt:message key="orders_page.canceled"/></option>
                        <option value="REJECTED"><fmt:message key="orders_page.rejected"/></option>
                        <option value="ACTIVE"><fmt:message key="orders_page.active"/></option>
                        <option value="COMPLETE"><fmt:message key="orders_page.complete"/></option>
                    </select>
                    <div class="form-group row">
                        <div class="col-4 offset-3">
                            <button type="submit" class="submit-button" id="butt">
                                <fmt:message key="orders_page.find"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <ctg:ordersPaginationTag/>
        <c:if test="${orders.size() == 0}">
            <div class="message-empty">
                <fmt:message key="orders_page.message"/>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
