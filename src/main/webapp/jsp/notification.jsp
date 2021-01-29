<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 10.11.2020
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="notification_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container notification-page">
        <c:if test="${registrationSuccess eq true}">
            <fmt:message key="notification_page.registration_success"/>
        </c:if>

        <c:if test="${addToolSuccess eq true}">
            <fmt:message key="notification_page.add_tool_success"/>
        </c:if>

        <c:if test="${updateToolSuccess eq true}">
            <fmt:message key="notification_page.update_tool_success"/>
        </c:if>

        <c:if test="${updateUserInfoSuccess eq true}">
            <fmt:message key="notification_page.update_user_info_success"/>
        </c:if>

        <c:if test="${updatePasswordSuccess eq true}">
            <fmt:message key="notification_page.update_password_success"/>
        </c:if>

        <c:if test="${confirmationSuccess eq true}">
            <fmt:message key="notification_page.confirm_registration_success"/>
        </c:if>
        <c:if test="${confirmationSuccess eq false}">
            <fmt:message key="notification_page.confirm_registration_fail"/>
        </c:if>

        <c:if test="${accessDenied eq true}">
            <fmt:message key="notification_page.access_denied"/>
        </c:if>

        <c:if test="${confirmEmail eq true}">
            <fmt:message key="notification_page.confirm_email"/>
        </c:if>

        <c:if test="${accountBlocked eq true}">
            <fmt:message key="notification_page.account_blocked"/>
        </c:if>

        <c:if test="${makeOrderSuccess eq true}">
            <fmt:message key="notification_page.make_order_success"/>
        </c:if>
        <c:if test="${makeOrderSuccess eq false}">
            <fmt:message key="notification_page.make_order_fail"/>
        </c:if>

        <c:if test="${payedOrderSuccess eq true}">
            <fmt:message key="notification_page.payed_order_success"/>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
