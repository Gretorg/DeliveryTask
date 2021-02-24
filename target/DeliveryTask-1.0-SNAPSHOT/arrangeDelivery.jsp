<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <form action="${pageContext.request.contextPath}/showConfirmForm" method="post">
                <h3><fmt:message key="body_route"/></h3>
                <div class="input-group mb-3">
                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="route_id">
                        <c:forEach var="Routes" items="${routes}">
                            <option value="<c:out value='${Routes.getId()}'/>">
                                <c:out value='${Routes.getCityFrom()}'/>-<c:out value='${Routes.getCityTo()}'/><br><br>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <h3><fmt:message key="body_weight"/></h3>
                <div class="input-group mb-3">
                    <input type="number" name="weight" class="form-control" placeholder="<fmt:message key="body_weight"/>" min = "1" max = "100" required>
                </div>
                <h3><fmt:message key="body_length"/></h3>
                <div class="input-group mb-3">
                    <input type="number" name="length" class="form-control" placeholder="<fmt:message key="body_length"/>" min = "1" max = "100" required>
                </div>
                <h3><fmt:message key="body_width"/></h3>
                <div class="input-group mb-3">
                    <input type="number" name="width" class="form-control" placeholder="<fmt:message key="body_width"/>" min = "1" max = "100" required>
                </div>
                <h3><fmt:message key="body_height"/></h3>
                <div class="input-group mb-3">
                    <input type="number" name="height" class="form-control" placeholder="<fmt:message key="body_height"/>" min = "1" max = "100" required>
                </div>
                <h3><fmt:message key="body_description"/></h3>
                <div class="input-group mb-3">
                    <input type="text" name="description" class="form-control" placeholder="<fmt:message key="body_description"/>" minlength="10" maxlength="100" required>
                </div>
                <h3><fmt:message key="body_receiver_name"/></h3>
                <div class="input-group mb-3">
                    <input type="text" name="first_name" class="form-control" placeholder="<fmt:message key="body_receiver_name"/>"  minlength="1" maxlength="20" required>
                </div>
                <h3><fmt:message key="body_receiver_surname"/></h3>
                <div class="input-group mb-3">
                    <input type="text" name="last_name" class="form-control" placeholder="<fmt:message key="body_receiver_surname"/>"  minlength="1" maxlength="40" required>
                </div>
                <h3><fmt:message key="body_address"/></h3>
                <div class="input-group mb-3">
                    <input type="text" name="address" class="form-control" placeholder="<fmt:message key="body_address"/>"  minlength="8" maxlength="50" required>
                </div>
                <div class="input-group">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button_arrange_delivery"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>