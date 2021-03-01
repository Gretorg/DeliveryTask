<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <table class="table table-primary">
                <thead>
                <tr>
                    <th scope="col">
                        <div class="btn-group">
                            <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <fmt:message key="body_city_from"/>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/showUserOrdersPagination?page=1&order=asc">
                                        Order by asc
                                    </a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/showUserOrdersPagination?page=1&order=desc">
                                        Order by desk
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </th>
                    <th scope="col"><fmt:message key="body_city_to"/></th>
                    <th scope="col"><fmt:message key="body_address"/></th>
                    <th scope="col"><fmt:message key="body_receiver_name"/></th>
                    <th scope="col"><fmt:message key="body_receiver_surname"/></th>
                    <th scope="col"><fmt:message key="body_send_date"/></th>
                    <th scope="col"><fmt:message key="body_delivery_date"/></th>
                    <th scope="col"><fmt:message key="body_price"/></th>
                    <th scope="col"><fmt:message key="body_status"/></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="deliveryList" items="${deliveryList}">
                        <tr>
                            <td> <c:out value='${deliveryList.getCityFrom()}'/></td>
                            <td> <c:out value='${deliveryList.getCityTo()}'/></td>
                            <td> <c:out value='${deliveryList.getAddress()}'/></td>
                            <td> <c:out value='${deliveryList.getReceiverName()}'/></td>
                            <td> <c:out value='${deliveryList.getReceiverSurname()}'/></td>
                            <td> <c:out value='${deliveryList.getSendDate()}'/></td>
                            <td> <c:out value='${deliveryList.getDeliveryDate()}'/></td>
                            <td> <c:out value='${deliveryList.getPrice()}'/></td>
                            <td> <c:out value='${deliveryList.getStatus()}'/></td>
                            <c:if test="${requestScope['status']== 1}">
                                <td>
                                    <a href="${pageContext.request.contextPath}/updateOrder?id=<c:out value='${deliveryList.getDeliveryId()}' />"
                                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">
                                        <fmt:message key="button_accept"/>
                                    </a>
                                </td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-12 d-flex justify-content-center align-items-center">
            <nav aria-label="...">
                <ul class="pagination">
                    <c:forEach var = "i" begin = "1" end = "${requestScope['countPages']}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/showUserOrdersPagination?page=${i}&order=<c:out value="${requestScope['order']}"/>">${i}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>