<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="registration_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="RegistrationForm" class="form-horizontal" method="post"
                      action="${pageContext.request.contextPath}/ToolRental">
                    <input type="hidden" name="command" value="registration"/>
                    <span class="heading">
                            <fmt:message key="login_page.registration"/>
                    </span>
                    <div class="form-group">
                        <input type="text" class="form-control" name="login"
                               title="<fmt:message key="registration_page.login"/>"
                               pattern="^\S{3,15}$"
                               required
                               placeholder="<fmt:message key="registration_page.login"/>"
                               value="${registrationParameters.get("login")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.login_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password"
                               title="<fmt:message key="registration_page.password"/>"
                               pattern="^\S{6,15}$"
                               required
                               placeholder="<fmt:message key="registration_page.password"/>"
                               value="${registrationParameters.get("password")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.password_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="name"
                               title="<fmt:message key="registration_page.name"/>"
                               pattern="^[a-zA-Zа-яА-я]{1,20}$"
                               required
                               placeholder="<fmt:message key="registration_page.name"/>"
                               value="${registrationParameters.get("name")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.name_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="surname"
                               title="<fmt:message key="registration_page.surname"/>"
                               pattern="^[a-zA-Zа-яА-я]{1,20}$"
                               required
                               placeholder="<fmt:message key="registration_page.surname"/>"
                               value="${registrationParameters.get("surname")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.surname_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email"
                               title="<fmt:message key="registration_page.email"/>"
                               pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}"
                               required
                               placeholder="<fmt:message key="registration_page.email"/>"
                               value="${registrationParameters.get("email")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.email_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-envelope"></i>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phone"
                               title="<fmt:message key="registration_page.phone"/>"
                               pattern="\+375\d{9}"
                               required
                               placeholder="<fmt:message key="registration_page.phone"/>"
                               value="${registrationParameters.get("phone")}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.phone_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-phone"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="registration_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${uniqueLoginError}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="registration_page.message.login_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${uniqueEmailError}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="registration_page.message.email_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${uniquePhoneError}">
                    <div class="form-group">
                        <label class="text"> <fmt:message key="registration_page.message.phone_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${registrationIncorrectData}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="registration_page.message.incorrect_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
