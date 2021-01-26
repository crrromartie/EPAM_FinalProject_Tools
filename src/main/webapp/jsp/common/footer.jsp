<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 04.11.2020
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title>Footer</title>
</head>
<body>
<div class="wrapper">
    <div class="container">
        <footer>
            <div class="footer">
                <span><fmt:message key="footer.copyright"/></span>
            </div>
        </footer>
    </div>
</div>
</body>
</html>
