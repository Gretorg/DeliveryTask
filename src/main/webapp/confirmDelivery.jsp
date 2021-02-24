<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>

<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <form action="${pageContext.request.contextPath}/confirmDelivery" method="post">
                <table class="table">
                    <tbody>
                    <tr>
                        <td><fmt:message key="body_weight"/></td>
                        <td>
                            <input type="number" name="weight" value="<c:out value="${requestScope['weight']}"/>" readonly hidden />
                            <c:out value="${requestScope['weight']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_length"/></td>
                        <td>
                            <input type="number" name="length" value="<c:out value="${requestScope['length']}"/>" readonly hidden />
                            <c:out value="${requestScope['length']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_width"/></td>
                        <td>
                            <input type="number" name="width" value="<c:out value="${requestScope['width']}"/>" readonly hidden />
                            <c:out value="${requestScope['width']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_height"/></td>
                        <td>
                            <input type="number" name="height" value="<c:out value="${requestScope['height']}"/>" readonly hidden />
                            <c:out value="${requestScope['height']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_description"/></td>
                        <td>
                            <input type="text" name="description" value="<c:out value="${requestScope['description']}"/>" readonly hidden />
                            <c:out value="${requestScope['description']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_send_date"/></td>
                        <td>
                            <input type="date" name="send_date" value="<c:out value="${requestScope['date']}"/>" readonly hidden />
                            <c:out value="${requestScope['date']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_delivery_date"/></td>
                        <td>
                            <input type="date" name="delivery_date" value="<c:out value="${requestScope['date2']}"/>" readonly hidden />
                            <c:out value="${requestScope['date2']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_receiver_name"/></td>
                        <td>
                            <input type="text" name="first_name" value="<c:out value="${requestScope['firstName']}"/>" readonly hidden />
                            <c:out value="${requestScope['firstName']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_receiver_surname"/></td>
                        <td>
                            <input type="text" name="last_name" value="<c:out value="${requestScope['lastName']}"/>" readonly hidden />
                            <c:out value="${requestScope['lastName']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_address"/></td>
                        <td>
                            <input type="text" name="address" value="<c:out value="${requestScope['address']}"/>" readonly hidden />
                            <c:out value="${requestScope['address']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="body_price"/></td>
                        <td>
                            <input type="number" name="price" value="<c:out value="${requestScope['price']}"/>" readonly hidden />
                            <c:out value="${requestScope['price']}"/>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <input type="hidden" name="user_id" value="<c:out value="${sessionScope['id']}"/>" />
                <input type="hidden" name="route_id" value="<c:out value="${requestScope['route_id']}"/>"/>
                <div class="input-group">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button_confirm"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>
