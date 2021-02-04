<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="profile_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="EditProfile" class="form-horizontal" action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <input type="hidden" name="command" value="edit_profile"/>
                    <span class="heading"><fmt:message key="profile_page.edit_profile"/></span>
                    <div class="form-group">
                        <img id="avatar" src="data:image/jpeg;base64,${user.getAvatar()}" alt="..."/>
                    </div>
                    <div class="form-group">
                        <a class="form-link"
                           href="${pageContext.request.contextPath}/ToolRental?command=avatar_pass">
                            <fmt:message key="profile_page.update_avatar"/></a>
                    </div>
                    <i class="fa fa-lock"></i>
                    <a class="form-link"
                       href="${pageContext.request.contextPath}/ToolRental?command=password_pass">
                        <fmt:message key="profile_page.update_password"/></a>
                    <div class="form-group">
                        <input type="text" class="form-control" name="name"
                               title="<fmt:message key="profile_page.name"/>"
                               pattern="^[a-zA-Zа-яА-я]{1,20}$"
                               required
                               placeholder="<fmt:message key="profile_page.name"/>"
                               value="${user.getName()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.name_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="surname"
                               title="<fmt:message key="profile_page.surname"/>"
                               pattern="^[a-zA-Zа-яА-я]{1,20}$"
                               required
                               placeholder="<fmt:message key="profile_page.surname"/>"
                               value="${user.getSurname()}"
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
                               placeholder="<fmt:message key="profile_page.email"/>"
                               value="${user.getEmail()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.email_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-envelope"></i>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phone"
                               title="<fmt:message key="profile_page.phone"/>"
                               pattern="\+375\d{9}"
                               required
                               placeholder="<fmt:message key="profile_page.phone"/>"
                               value="${user.getPhone()}"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.phone_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-phone"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="profile_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${uniqueEmailError}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="profile_page.message.email_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${uniquePhoneError}">
                    <div class="form-group">
                        <label class="text"> <fmt:message key="profile_page.message.phone_not_unique"/></label>
                    </div>
                </c:if>
                <c:if test="${userEditIncorrectData}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="profile_page.message.incorrect_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
