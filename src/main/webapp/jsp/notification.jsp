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
        <c:if test="${registrationSuccess}">
            <label class="alert-success"><fmt:message key="notification_page.registration_success"/></label>
        </c:if>
        <c:if test="${addToolSuccess}">
            <label class="alert-success"><fmt:message key="notification_page.add_tool_success"/></label>
        </c:if>
        <c:if test="${updateToolSuccess}">
            <label class="alert-success"><fmt:message key="notification_page.update_tool_success"/></label>
        </c:if>
        <c:if test="${updateUserInfoSuccess}">
            <label class="alert-success"><fmt:message key="notification_page.update_user_info_success"/></label>
        </c:if>
        <c:if test="${updatePasswordSuccess}">
            <label class="alert-success"><fmt:message key="notification_page.update_password_success"/></label>
        </c:if>
        <c:if test="${not empty confirmationSuccess}">
            <c:if test="${confirmationSuccess}">
                <label class="alert-success"><fmt:message key="notification_page.confirm_registration_success"/></label>
            </c:if>
            <c:if test="${!confirmationSuccess}">
                <label class="alert-warning"><fmt:message key="notification_page.confirm_registration_fail"/></label>
            </c:if>
        </c:if>
        <c:if test="${not empty makeOrderSuccess}">
            <c:if test="${makeOrderSuccess}">
                <label class="alert-success"><fmt:message key="notification_page.make_order_success"/></label>
            </c:if>
            <c:if test="${!makeOrderSuccess}">
                <label class="alert-warning"><fmt:message key="notification_page.make_order_fail"/></label>
            </c:if>
        </c:if>
        <c:if test="${payedOrderSuccess}">
            <label class="alert-success"><fmt:message key="notification_page.payed_order_success"/></label>
        </c:if>
        <c:if test="${confirmEmail}">
            <label class="alert-warning"><fmt:message key="notification_page.confirm_email"/></label>
        </c:if>
        <c:if test="${accessDenied}">
            <label class="alert-danger"><fmt:message key="notification_page.access_denied"/></label>
        </c:if>
        <c:if test="${accountBlocked}">
            <label class="alert-danger"><fmt:message key="notification_page.account_blocked"/></label>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
