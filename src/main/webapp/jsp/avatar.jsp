<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="avatar_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-6 offset-3">
                <form name="UpdateAvatar" class="form-horizontal" enctype="multipart/form-data"
                      action="${pageContext.request.contextPath}/ToolRental"
                      method="post">
                    <span class="heading"><fmt:message key="avatar_page.update_avatar"/></span>
                    <div class="form-group">
                        <img id="avatar" src="data:image/jpeg;base64,${user.getAvatar()}" alt="..."/>
                    </div>
                    <input type="hidden" name="command" value="update_avatar"/>
                    <input type="file" name="avatar"
                           accept="image/jpg" required/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">
                            <fmt:message key="avatar_page.update"/>
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
