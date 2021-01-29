<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 27.01.2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="password_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="EditProfile" class="form-horizontal" action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <input type="hidden" name="command" value="update_password"/>
                    <span class="heading"><fmt:message key="password_page.update_password"/></span>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password"
                               title="<fmt:message key="password_page.password"/>"
                               pattern="^\S{6,15}$"
                               required
                               placeholder="<fmt:message key="password_page.password"/>"
                               oninvalid="this.setCustomValidity('<fmt:message
                                       key="oninvalid.password_pattern"/>')"
                               onchange="this.setCustomValidity('')"/>
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="profile_page.submit"/>
                        </button>
                    </div>
                </form>
                <c:if test="${passwordIncorrectData eq true}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="password_page.incorrect_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
