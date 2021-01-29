<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 24.01.2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
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
                        <option selected value="ACTIVE"><fmt:message key="clients_page.active"/></option>
                        <option value="UNCONFIRMED"><fmt:message key="clients_page.unconfirmed"/></option>
                        <option value="BLOCKED"><fmt:message key="clients_page.blocked"/></option>
                    </select>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary" id="but-client-find">
                                <fmt:message key="clients_page.find"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <c:if test="${users.size() == 0}">
            <div class="message-empty">
                <fmt:message key="client_page.message"/>
            </div>
        </c:if>
        <c:if test="${users.size() != 0}">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="clients_page.avatar"/></th>
                    <th scope="col"><fmt:message key="clients_page.login"/></th>
                    <th scope="col"><fmt:message key="clients_page.name_surname"/></th>
                    <th scope="col"><fmt:message key="clients_page.email"/></th>
                    <th scope="col"><fmt:message key="clients_page.phone"/></th>
                    <th scope="col"><fmt:message key="clients_page.status"/></th>
                    <th scope="col"><fmt:message key="clients_page.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" begin="${(usersPageNumber * pageEntries - pageEntries)}"
                           end="${(users.size() < (usersPageNumber * pageEntries) ? users.size() : (usersPageNumber * pageEntries)) - 1}">
                    <tr>
                        <c:if test="${users.get(i).getRole() == 'CLIENT'}">
                            <td>
                                <img class="card-img-top" id="avatar_mini"
                                     src="data:image/jpeg;base64,${users.get(i).getAvatar()}"/>
                            </td>
                            <td>${users.get(i).getLogin()}</td>
                            <td>${users.get(i).getName()} ${users.get(i).getSurname()}</td>
                            <td>${users.get(i).getEmail()}</td>
                            <td>${users.get(i).getPhone()}</td>
                            <c:choose>
                                <c:when test="${users.get(i).getStatus() eq 'ACTIVE'}">
                                    <td><fmt:message key="clients_page.active"/></td>
                                </c:when>
                                <c:when test="${users.get(i).getStatus() eq 'UNCONFIRMED'}">
                                    <td><fmt:message key="clients_page.unconfirmed"/></td>
                                </c:when>
                                <c:when test="${users.get(i).getStatus() eq 'BLOCKED'}">
                                    <td><fmt:message key="clients_page.blocked"/></td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:if test="${users.get(i).getStatus() == 'ACTIVE' || users.get(i).getStatus() == 'UNCONFIRMED'}">
                                    <form name="BlockUser" action="${pageContext.request.contextPath}/ToolRental"
                                          method="get">
                                        <input type="hidden" name="command" value="block_client">
                                        <input type="hidden" name="login" value="${users.get(i).getLogin()}">
                                        <button type="submit" class="submit-button" id="blockUser">
                                            <fmt:message key="clients_page.block"/>
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${users.get(i).getStatus() == 'BLOCKED'}">
                                    <form name="UnblockUser" action="${pageContext.request.contextPath}/ToolRental"
                                          method="get">
                                        <input type="hidden" name="command" value="unblock_client">
                                        <input type="hidden" name="login" value="${users.get(i).getLogin()}">
                                        <button type="submit" class="submit-button" id="unblockUser">
                                            <fmt:message key="clients_page.unblock"/>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ctg:usersPagination usersPageNumber="${usersPageNumber}" pageEntries="${pageEntries}"/>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
