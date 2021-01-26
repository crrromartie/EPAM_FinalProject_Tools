<%--
  Created by IntelliJ IDEA.
  User: IHOR
  Date: 18.12.2020
  Time: 23:27
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
    <title><fmt:message key="tools_page.title"/></title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/header.jsp"/>
<div class="wrapper">
    <div class="container default-page">
        <div class="row">
            <div class="col-3">
                <form name="ToolsType" action="${pageContext.request.contextPath}/ToolRental" method="post">
                    <input type="hidden" name="command" value="filter_tools"/>
                    <select class="custom-select" id="select" name="toolType">
                        <option selected value="CHAINSAW"><fmt:message key="tool_s_page.chainsaw"/></option>
                        <option value="CONCRETE_SAW"><fmt:message key="tool_s_page.concrete_saw"/></option>
                        <option value="JIGSAW"><fmt:message key="tool_s_page.jigsaw"/></option>
                        <option value="PETROL_CUTTER"><fmt:message key="tool_s_page.petrol_cutter"/></option>
                        <option value="TILE_CUTTER"><fmt:message key="tool_s_page.tile_cutter"/></option>
                        <option value="BREAKER_HAMMER"><fmt:message key="tool_s_page.breaker_hammer"/></option>
                        <option value="SCREW"><fmt:message key="tool_s_page.screw"/></option>
                        <option value="DRILL"><fmt:message key="tool_s_page.drill"/></option>
                        <option value="PERFORATOR"><fmt:message key="tool_s_page.perforator"/></option>
                        <option value="WELDER"><fmt:message key="tool_s_page.welder"/></option>
                    </select>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary" id="but-tool-find">
                                <fmt:message key="tool_s_page.find"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-3">
                <label><fmt:message key="tool_s_page.sort_price"/></label>
                <form name="toolsSort" action="${pageContext.request.contextPath}/ToolRental" method="get">
                    <input type="hidden" name="command" value="sort_tools">
                    <input type="hidden" name="toolsSortCriteria" value="price_up">
                    <button type="submit" class="btn btn-primary" id="but-sort-up">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-arrow-up-square" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                  d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm8.5 9.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z"/>
                        </svg>
                    </button>
                </form>
                <form name="toolsSort" action="${pageContext.request.contextPath}/ToolRental" method="get">
                    <input type="hidden" name="command" value="sort_tools">
                    <input type="hidden" name="toolsSortCriteria" value="price_down">
                    <button type="submit" class="btn btn-primary" id="but-sort-down">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-arrow-down-square" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                  d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm8.5 2.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z"/>
                        </svg>
                    </button>
                </form>
            </div>
            <div class="col-2">
                <label><fmt:message key="tool_s_page.display"/></label>
                <form name="toolsDisplay" action="${pageContext.request.contextPath}/ToolRental" method="get">
                    <input type="hidden" name="command" value="change_tools_display">
                    <input type="hidden" name="toolsDisplay" value="toolsList">
                    <button type="submit" class="btn btn-primary" id="but-view-list">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-list"
                             viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                  d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                        </svg>
                    </button>
                </form>
                <form name="toolsDisplay" action="${pageContext.request.contextPath}/ToolRental" method="get">
                    <input type="hidden" name="command" value="change_tools_display">
                    <input type="hidden" name="toolsDisplay" value="toolsCards">
                    <button type="submit" class="btn btn-primary" id="but-view-cards">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-grid-3x3-gap-fill" viewBox="0 0 16 16">
                            <path d="M1 2a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2zm5 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V2zm5 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1V2zM1 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V7zm5 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm5 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1V7zM1 12a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-2zm5 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1v-2zm5 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-2z"></path>
                        </svg>
                    </button>
                </form>
            </div>
            <div class="col-2">
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/ToolRental?command=add_tool_pass">
                        <fmt:message key="tool_s_page.add_new"/></a>
                </c:if>
            </div>
        </div>
        <c:if test="${tools.size() == 0}">
            <div class="message-empty">
                <fmt:message key="tool_s_page.message"/>
            </div>
        </c:if>
        <c:if test="${tools.size() != 0}">
            <c:if test="${toolsDisplay eq 'toolsCards'}">
                <div class="row">
                    <c:forEach var="i" begin="${(toolsPageNumber * pageEntries - pageEntries)}"
                               end="${(tools.size() < (toolsPageNumber * pageEntries) ? tools.size() : (toolsPageNumber * pageEntries)) - 1}">
                        <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                            <div class="card h-100">
                                <img class="card-img-top" id="tool_photo_card"
                                     src="data:image/jpeg;base64,${tools.get(i).getPhoto()}"/>
                                <div class="card-body">
                                    <p class="card-text">
                                        <c:choose>
                                            <c:when test="${tools.get(i).getType() eq 'CHAINSAW'}">
                                                <fmt:message key="tool_s_page.chainsaw"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'CONCRETE_SAW'}">
                                                <fmt:message key="tool_s_page.concrete_saw"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'JIGSAW'}">
                                                <fmt:message key="tool_s_page.jigsaw"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'PETROL_CUTTER'}">
                                                <fmt:message key="tool_s_page.petrol_cutter"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'TILE_CUTTER'}">
                                                <fmt:message key="tool_s_page.tile_cutter"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'BREAKER_HAMMER'}">
                                                <fmt:message key="tool_s_page.breaker_hammer"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'SCREW'}">
                                                <fmt:message key="tool_s_page.screw"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'DRILL'}">
                                                <fmt:message key="tool_s_page.drill"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'PERFORATOR'}">
                                                <fmt:message key="tool_s_page.perforator"/>
                                            </c:when>
                                            <c:when test="${tools.get(i).getType() eq 'WELDER'}">
                                                <fmt:message key="tool_s_page.welder"/>
                                            </c:when>
                                        </c:choose>
                                    </p>
                                    <h5 class="card-title">
                                        <c:if test="${sessionScope.role == 'CLIENT'}">
                                            <c:if test="${tools.get(i).isAvailable() eq true}">
                                                <a href="${pageContext.request.contextPath}/ToolRental?command=tool_pass&toolId=${tools.get(i).getToolId()}"/>
                                                ${tools.get(i).getModel()}
                                                </a>
                                            </c:if>
                                            <c:if test="${tools.get(i).isAvailable() eq false}">
                                                <label> ${tools.get(i).getModel()}</label>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${sessionScope.role == 'GUEST'}">
                                            <label> ${tools.get(i).getModel()}</label>
                                        </c:if>
                                        <c:if test="${sessionScope.role == 'ADMIN'}">
                                            <a href="${pageContext.request.contextPath}/ToolRental?command=tool_edit_pass&toolId=${tools.get(i).getToolId()}"/>
                                            ${tools.get(i).getModel()}
                                            </a>
                                        </c:if>
                                    </h5>
                                    <p class="card-text">${tools.get(i).getRentPrice()}</p>
                                    <p class="card-text">
                                        <c:if test="${tools.get(i).isAvailable() eq true}">
                                            <label> <fmt:message key="tool_s_page.availability.available"/></label>
                                        </c:if>
                                        <c:if test="${tools.get(i).isAvailable() eq false}">
                                            <label><fmt:message key="tool_s_page.availability.not_available"/></label>
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <ctg:toolsPagination toolsPageNumber="${toolsPageNumber}" pageEntries="${pageEntries}"/>
            </c:if>
            <c:if test="${toolsDisplay eq 'toolsList'}">
                <div class="container" id="tools_table">
                    <table class="table" id="listTable">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="tool_s_page.type"/></th>
                            <th scope="col"><fmt:message key="tool_s_page.model"/></th>
                            <th scope="col"><fmt:message key="tool_s_page.rent_price"/></th>
                            <th scope="col"><fmt:message key="tool_s_page.availability"/></th>
                            <th scope="col"><fmt:message key="tool_s_page.photo"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="i" begin="${(toolsPageNumber * pageEntries - pageEntries)}"
                                   end="${(tools.size() < (toolsPageNumber * pageEntries) ? tools.size() : (toolsPageNumber * pageEntries)) - 1}">
                            <tr>
                                <c:choose>
                                    <c:when test="${tools.get(i).getType() eq 'CHAINSAW'}">
                                        <td><fmt:message key="tool_s_page.chainsaw"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'CONCRETE_SAW'}">
                                        <td><fmt:message key="tool_s_page.concrete_saw"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'JIGSAW'}">
                                        <td><fmt:message key="tool_s_page.jigsaw"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'PETROL_CUTTER'}">
                                        <td><fmt:message key="tool_s_page.petrol_cutter"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'TILE_CUTTER'}">
                                        <td><fmt:message key="tool_s_page.tile_cutter"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'BREAKER_HAMMER'}">
                                        <td><fmt:message key="tool_s_page.breaker_hammer"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'SCREW'}">
                                        <td><fmt:message key="tool_s_page.screw"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'DRILL'}">
                                        <td><fmt:message key="tool_s_page.drill"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'PERFORATOR'}">
                                        <td><fmt:message key="tool_s_page.perforator"/></td>
                                    </c:when>
                                    <c:when test="${tools.get(i).getType() eq 'WELDER'}">
                                        <td><fmt:message key="tool_s_page.welder"/></td>
                                    </c:when>
                                </c:choose>
                                <td>
                                    <c:if test="${sessionScope.role == 'CLIENT'}">
                                        <c:if test="${tools.get(i).isAvailable() eq true}">
                                            <a href="${pageContext.request.contextPath}/ToolRental?command=tool_pass&toolId=${tools.get(i).getToolId()}"/>
                                            ${tools.get(i).getModel()}
                                            </a>
                                        </c:if>
                                        <c:if test="${tools.get(i).isAvailable() eq false}">
                                            <label> ${tools.get(i).getModel()}</label>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.role == 'GUEST'}">
                                        <label> ${tools.get(i).getModel()}</label>
                                    </c:if>
                                    <c:if test="${sessionScope.role == 'ADMIN'}">
                                        <a href="${pageContext.request.contextPath}/ToolRental?command=tool_edit_pass&toolId=${tools.get(i).getToolId()}"/>
                                        ${tools.get(i).getModel()}
                                        </a>
                                    </c:if>
                                </td>
                                <td>${tools.get(i).getRentPrice()}</td>
                                <td>
                                    <c:if test="${tools.get(i).isAvailable() eq true}">
                                        <label> <fmt:message key="tool_s_page.availability.available"/></label>
                                    </c:if>
                                    <c:if test="${tools.get(i).isAvailable() eq false}">
                                        <label><fmt:message key="tool_s_page.availability.not_available"/></label>
                                    </c:if>
                                </td>
                                <td>
                                    <img class="card-img-top" id="tool_photo_list"
                                         src="data:image/jpeg;base64,${tools.get(i).getPhoto()}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <ctg:toolsPagination toolsPageNumber="${toolsPageNumber}" pageEntries="${pageEntries}"/>
            </c:if>
        </c:if>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/common/footer.jsp"/>
</body>
</html>
