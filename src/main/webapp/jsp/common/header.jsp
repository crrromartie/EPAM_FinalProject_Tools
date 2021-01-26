<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 04.11.2020
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forms.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Header</title>
</head>
<body>
<div class="wrapper">
    <div class="container">
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/ToolRental?command=home_pass">
                        <c:out value="ToolRental"/>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/ToolRental?command=home_pass">
                                    <fmt:message key="header.home"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/ToolRental?command=tools_pass">
                                    <fmt:message key="header.tools"/></a>
                            </li>
                            <c:if test="${sessionScope.role == 'CLIENT'}">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/ToolRental?command=orders_client_pass">
                                        <fmt:message key="header.orders"/></a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/ToolRental?command=orders_admin_pass">
                                        <fmt:message key="header.orders"/></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/ToolRental?command=clients_pass">
                                        <fmt:message key="header.clients"/></a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.role == 'GUEST'}">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/ToolRental?command=login_pass">
                                        <fmt:message key="header.login"/></a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.role == 'CLIENT' || sessionScope.role == 'ADMIN'}">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/ToolRental?command=profile_pass">
                                        <fmt:message key="header.profile"/></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/ToolRental?command=logout">
                                        <fmt:message key="header.logout"/></a>
                                </li>
                                <li class="nav-item">
                                    <img class="card-img-top" id="avatar_mini"
                                         src="data:image/jpeg;base64,${user.getAvatar()}"/>
                                </li>
                            </c:if>
                        </ul>
                        <div class="col" id="lang_switch" style="max-width: 70px">
                            <div class="row">
                                <div class="col text-right">
                                    <a href="${pageContext.request.contextPath}/ToolRental?command=change_locale&lang=ru">
                                        <fmt:message key="header.language_ru"/>
                                    </a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col text-right">
                                    <a href="${pageContext.request.contextPath}/ToolRental?command=change_locale&lang=en">
                                        <fmt:message key="header.language_en"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    </div>
</div>
</body>
</html>
