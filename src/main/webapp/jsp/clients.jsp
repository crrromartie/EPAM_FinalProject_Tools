<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="properties.pagecontent"/>
<html>
<head>
    <title><fmt:message key="clients_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-3">
                <form name="ClientStatus" action="${pageContext.request.contextPath}/ToolRental" method="post">
                    <input type="hidden" name="command" value="filter_clients"/>
                    <select class="custom-select" id="select" name="clientStatus">
                        <option selected value="ALL"><fmt:message key="clients_page.all"/></option>
                        <option value="ACTIVE"><fmt:message key="clients_page.active"/></option>
                        <option value="UNCONFIRMED"><fmt:message key="clients_page.unconfirmed"/></option>
                        <option value="BLOCKED"><fmt:message key="clients_page.blocked"/></option>
                    </select>
                    <div class="form-group row">
                        <div class="col-4 offset-3">
                            <button type="submit" class="submit-button" id="butt">
                                <fmt:message key="clients_page.find"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <ctg:usersPaginationTag/>
        <c:if test="${users.size() == 0}">
            <div class="message-empty">
                <fmt:message key="client_page.message"/>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
