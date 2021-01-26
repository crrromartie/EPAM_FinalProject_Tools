<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 27.12.2020
  Time: 20:34
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
    <title><fmt:message key="orders_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-3">
                <form name="OrdersType" action="${pageContext.request.contextPath}/ToolRental" method="post">
                    <input type="hidden" name="command" value="filter_orders"/>
                    <select class="custom-select" id="select" name="orderStatus">
                        <option selected value="NEW"><fmt:message key="orders_page.new"/></option>
                        <option value="APPROVED"><fmt:message key="orders_page.approved"/></option>
                        <option value="CANCELED"><fmt:message key="orders_page.canceled"/></option>
                        <option value="REJECTED"><fmt:message key="orders_page.rejected"/></option>
                        <option value="ACTIVE"><fmt:message key="orders_page.active"/></option>
                        <option value="COMPLETE"><fmt:message key="orders_page.complete"/></option>
                    </select>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary" id="but-order-find">
                                <fmt:message key="orders_page.find"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <c:if test="${orders.size() == 0}">
            <div class="message-empty">
                <fmt:message key="orders_page.message"/>
            </div>
        </c:if>
        <c:if test="${orders.size() != 0}">
            <table class="table">
                <thead>
                <tr>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <th scope="col"><fmt:message key="orders_page.login"/></th>
                        <th scope="col"><fmt:message key="orders_page.name_surname"/></th>
                        <th scope="col"><fmt:message key="orders_page.email"/></th>
                    </c:if>
                    <th scope="col"><fmt:message key="orders_page.tool_model"/></th>
                    <th scope="col"><fmt:message key="orders_page.order_date"/></th>
                    <th scope="col"><fmt:message key="orders_page.return_date"/></th>
                    <th scope="col"><fmt:message key="orders_page.total_cost"/></th>
                    <th scope="col"><fmt:message key="orders_page.status"/></th>
                    <th scope="col"><fmt:message key="orders_page.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" begin="${(ordersPageNumber * pageEntries - pageEntries)}"
                           end="${(orders.size() < (ordersPageNumber * pageEntries) ? orders.size() : (ordersPageNumber * pageEntries)) - 1}">
                    <tr>
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <td>${orders.get(i).getUser().getLogin()}</td>
                            <td>${orders.get(i).getUser().getName()} ${orders.get(i).getUser().getSurname()}</td>
                            <td>${orders.get(i).getUser().getEmail()}</td>
                        </c:if>
                        <td>${orders.get(i).getTool().getModel()}</td>
                        <td>${orders.get(i).getOrderDate()}</td>
                        <td>${orders.get(i).getReturnDate()}</td>
                        <td>${orders.get(i).getTotalCost()}</td>
                        <c:choose>
                            <c:when test="${orders.get(i).getStatus() eq 'NEW'}">
                                <td><fmt:message key="orders_page.new"/></td>
                            </c:when>
                            <c:when test="${orders.get(i).getStatus() eq 'APPROVED'}">
                                <td><fmt:message key="orders_page.approved"/></td>
                            </c:when>
                            <c:when test="${orders.get(i).getStatus() eq 'CANCELED'}">
                                <td><fmt:message key="orders_page.canceled"/></td>
                            </c:when>
                            <c:when test="${orders.get(i).getStatus() eq 'REJECTED'}">
                                <td><fmt:message key="orders_page.rejected"/></td>
                            </c:when>
                            <c:when test="${orders.get(i).getStatus() eq 'ACTIVE'}">
                                <td><fmt:message key="orders_page.active"/></td>
                            </c:when>
                            <c:when test="${orders.get(i).getStatus() eq 'COMPLETE'}">
                                <td><fmt:message key="orders_page.complete"/></td>
                            </c:when>
                        </c:choose>
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <td>
                                <c:if test="${orders.get(i).getStatus() == 'NEW'}">
                                    <form name="OrderApprove" action="${pageContext.request.contextPath}/ToolRental"
                                          method="get">
                                        <input type="hidden" name="command" value="order_approve">
                                        <input type="hidden" name="orderId" value="${orders.get(i).getOrderId()}">
                                        <button type="submit" class="btn btn-primary" id="but-approve">
                                            <fmt:message key="orders_page.approve"/>
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${orders.get(i).getStatus() == 'NEW' || orders.get(i).getStatus() == 'APPROVED'}">
                                    <form name="OrderReject" action="${pageContext.request.contextPath}/ToolRental"
                                          method="get">
                                        <input type="hidden" name="command" value="order_reject">
                                        <input type="hidden" name="orderId" value="${orders.get(i).getOrderId()}">
                                        <button type="submit" class="btn btn-primary" id="but-reject">
                                            <fmt:message key="orders_page.reject"/>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </c:if>
                        <c:if test="${sessionScope.role == 'CLIENT'}">
                            <td>
                                <c:if test="${orders.get(i).getStatus() == 'NEW' || orders.get(i).getStatus() == 'APPROVED'}">
                                    <form name="OrderCancel" action="${pageContext.request.contextPath}/ToolRental"
                                          method="get">
                                        <input type="hidden" name="command" value="order_cancel">
                                        <input type="hidden" name="orderId" value="${orders.get(i).getOrderId()}">
                                        <button type="submit" class="btn btn-primary" id="but-cancel">
                                            <fmt:message key="orders_page.cancel"/>
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${orders.get(i).getStatus() == 'APPROVED'}">
                                    <form name="PayPass" action="${pageContext.request.contextPath}/ToolRental"
                                          method="get">
                                        <input type="hidden" name="command" value="payment_pass">
                                        <input type="hidden" name="orderId" value="${orders.get(i).getOrderId()}">
                                        <button type="submit" class="btn btn-primary" id="but-pay-pass">
                                            <fmt:message key="orders_page.pay"/>
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ctg:ordersPagination ordersPageNumber="${ordersPageNumber}" pageEntries="${pageEntries}"/>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
