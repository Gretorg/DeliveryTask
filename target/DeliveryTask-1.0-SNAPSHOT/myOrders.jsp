<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <table class="table table-primary">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="body_city_from"/></th>
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
                        <td><c:out value='${deliveryList.getDeliverySelect().getCityFrom()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getCityTo()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getAddress()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getReceiverName()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getReceiverSurname()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getSendDate()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getDeliveryDate()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getPrice()}'/></td>
                        <td><c:out value='${deliveryList.getDeliverySelect().getStatus()}'/></td>
                        <c:if test="${requestScope['status']== 2}">
                            <td>
                                <a href="${pageContext.request.contextPath}/payForOrder?id=<c:out value='${deliveryList.getDeliverySelect().getDeliveryId()}' />"
                                   class="btn btn-primary btn-lg active" role="button" aria-pressed="true">
                                    <fmt:message key="button_pay"/>
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>