<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="login_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="Login" class="form-horizontal" method="post"
                      action="${pageContext.request.contextPath}/ToolRental">
                    <input type="hidden" name="command" value="login"/>
                    <span class="heading">
                            <fmt:message key="login_page.login"/>
                    </span>
                    <div class="form-group">
                        <input type="text" class="form-control" name="login" required
                               title="<fmt:message key="login_page.login"/>"
                               placeholder="<fmt:message key="login_page.login"/>"
                               value="${login}"/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" required
                               title="<fmt:message key="login_page.password"/>"
                               placeholder="<fmt:message key="login_page.password"/>"
                               value="${password}"/>
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="login_page.submit"/>
                        </button>
                        <span class="text"><a
                                class="form-link"
                                href="${pageContext.request.contextPath}/ToolRental?command=registration_pass">
                            <fmt:message key="login_page.register"/></a>
                        </span>
                    </div>
                </form>
                <c:if test="${loginIncorrectData}">
                    <div class="form-group">
                        <label class="text"><fmt:message key="login_page.message.incorrect_data"/></label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
