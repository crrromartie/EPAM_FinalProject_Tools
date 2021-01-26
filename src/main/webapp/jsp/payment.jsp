<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 23.01.2021
  Time: 21:05
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
    <title><fmt:message key="payment_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-lg-7 mx-auto" id="payment-form">
                <div class="rounded-lg p-5">
                    <ul role="tablist" class="nav nav-fill">
                        <li class="nav-item">
                            <label>
                                <i class="fa fa-credit-card"></i>
                                <fmt:message key="payment_page.payment"/>
                            </label>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="nav-tab-card" class="tab-pane fade show active">
                            <form name="PaymentForm" action="${pageContext.request.contextPath}/ToolRental"
                                  method="post">
                                <div class="form-group">
                                    <label for="card_number"><fmt:message key="payment_page.card_number"/></label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="card_number" name="cardNumber"
                                               placeholder="<fmt:message key="payment_page.placeholder_card_number"/>"
                                               pattern="^[\d]{4}\s[\d]{4}\s[\d]{4}\s[\d]{4}$"
                                               required
                                               value="${paymentParameters.get("cardNumber")}"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="oninvalid.card_number"/>')"
                                               onchange="this.setCustomValidity('')"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text text-muted">
                                                <i class="fa fa-cc-visa mx-1"></i>
                                                <i class="fa fa-cc-amex mx-1"></i>
                                                <i class="fa fa-cc-mastercard mx-1"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-8">
                                        <div class="form-group">
                                            <label><span class="hidden-xs"><fmt:message
                                                    key="payment_page.expiration"/></span></label>
                                            <div class="input-group">
                                                <input type="number" class="form-control" name="cardMonth"
                                                       placeholder="<fmt:message key="payment_page.placeholder_month"/>"
                                                       min="1"
                                                       max="12"
                                                       required
                                                       value="${paymentParameters.get("cardMonth")}"
                                                       oninvalid="this.setCustomValidity('<fmt:message
                                                               key="oninvalid.month"/>')"
                                                       onchange="this.setCustomValidity('')"/>
                                                <input type="number" class="form-control" name="cardYear"
                                                       placeholder="<fmt:message key="payment_page.placeholder_year"/>"
                                                       min="21"
                                                       max="26"
                                                       required
                                                       value="${paymentParameters.get("cardYear")}"
                                                       oninvalid="this.setCustomValidity('<fmt:message
                                                               key="oninvalid.year"/>')"
                                                       onchange="this.setCustomValidity('')"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group mb-4">
                                            <label data-toggle="tooltip"><fmt:message
                                                    key="payment_page.cvv"/></label>
                                            <input type="text" class="form-control" name="cardCvv"
                                                   placeholder="<fmt:message key="payment_page.placeholder_cvv"/>"
                                                   pattern="[0-9]{3}"
                                                   required
                                                   value="${paymentParameters.get("cardCvv")}"
                                                   oninvalid="this.setCustomValidity('<fmt:message
                                                           key="oninvalid.cvv"/>')"
                                                   onchange="this.setCustomValidity('')">
                                        </div>
                                    </div>
                                </div>
                                <div class="input">
                                    <input type="hidden" name="command" value="make_payment">
                                    <button type="submit" class="submit-button btn btn-block shadow-sm"
                                            id="but-pay">
                                        <fmt:message key="payment_page.submit"/> ${sessionScope.order.totalCost}
                                    </button>
                                </div>
                            </form>
                            <c:if test="${paymentIncorrectData eq true}">
                                <div class="form-group">
                                    <label class="text"><fmt:message key="payment_page.message.incorrect_data"/></label>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
