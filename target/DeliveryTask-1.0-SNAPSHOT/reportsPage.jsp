<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="col-12 d-flex justify-content-center align-items-center">
        <h1>HOME PAGE</h1>
    </div>
    <div class="row">
        <div class="col-4">
            <form action="${pageContext.request.contextPath}/findReport" method="post">
                <div class="mb-3">
                    <label for="select_date" class="form-label">
                        <fmt:message key="body_send_date"/>
                    </label>
                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="date" id="select_date">
                        <c:forEach var="dateList" items="${requestScope['dateList']}">
                            <option value="<c:out value='${dateList}'/>">
                                <c:out value='${dateList}'/><br><br>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="select_city" class="form-label">
                        <fmt:message key="body_city_from"/>
                    </label>
                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="city" id="select_city">
                        <c:forEach var="cityList" items="${requestScope['cityList']}">
                            <option value="<c:out value='${cityList}'/>">
                                <c:out value='${cityList}'/><br><br>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <button type="submit" class="btn btn-primary"><fmt:message key="button_find"/></button>
                </div>
            </form>
        </div>
        <div class="col-8">
            <c:if test="${requestScope['show'] == 1}">
                <div class="col-12 d-flex justify-content-center">
                    <form action="${pageContext.request.contextPath}/saveReport" method="post">
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
                                    <td>
                                        <input type="hidden" name="cityFrom"
                                               value="<c:out value='${deliveryList.getCityFrom()}'/>">
                                        <input type="hidden" name="dateSend"
                                               value="<c:out value='${deliveryList.getSendDate()}'/>">
                                        <c:out value='${deliveryList.getCityFrom()}'/>
                                    </td>
                                    <td><c:out value='${deliveryList.getCityTo()}'/></td>
                                    <td><c:out value='${deliveryList.getAddress()}'/></td>
                                    <td><c:out value='${deliveryList.getReceiverName()}'/></td>
                                    <td><c:out value='${deliveryList.getReceiverSurname()}'/></td>
                                    <td><c:out value='${deliveryList.getSendDate()}'/></td>
                                    <td><c:out value='${deliveryList.getDeliveryDate()}'/></td>
                                    <td><c:out value='${deliveryList.getPrice()}'/></td>
                                    <td><c:out value='${deliveryList.getStatus()}'/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="input-group mb-3">
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button_save"/>
                            </button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>
