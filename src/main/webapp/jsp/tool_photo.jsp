<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 26.01.2021
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="tool_photo_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="UpdateImage" class="form-horizontal" enctype="multipart/form-data"
                      action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <span class="heading"><fmt:message key="tool_photo_page.update_photo"/></span>
                    <div class="form-group">
                        <img class="card-img-top" id="tool_photo_card"
                             src="data:image/jpeg;base64,${tool.getPhoto()}"/>
                    </div>
                    <input type="hidden" name="command" value="update_tool_photo"/>
                    <input id="photo" type="file" name="toolPhoto"
                           accept="image/jpg" required/>
                    <input type="hidden" name="toolId" value="${tool.getToolId()}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="tool_photo_page.update"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>