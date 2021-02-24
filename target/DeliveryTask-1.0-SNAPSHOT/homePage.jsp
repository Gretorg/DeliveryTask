<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <h2><fmt:message key="home_info"/></h2>
        </div>
        <div class="col-12 d-flex justify-content-center align-items-center">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                <fmt:message key="home_tariffs"/>
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="#header-modalLabel">
                                <fmt:message key="home_tariffs"/>
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <td><fmt:message key="body_distance"/></td>
                                    <td><fmt:message key="home_distance"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="body_weight"/></td>
                                    <td><fmt:message key="home_weight"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="body_length"/></td>
                                    <td><fmt:message key="home_length"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="body_height"/></td>
                                    <td><fmt:message key="home_height"/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="body_width"/></td>
                                    <td><fmt:message key="home_width"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 d-flex justify-content-center align-items-center">
            <form action="${pageContext.request.contextPath}/price" method="post">
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
                <div class="input-group mb-3">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button_get_price"/>
                    </button>
                </div>

            </form>
        </div>
        <div class="col-12 d-flex justify-content-center align-items-center">
            <c:if test="${requestScope['price'] != null}">
                <h3><fmt:message key="body_price"/> ₴ <c:out value="${requestScope['price']}"/></h3>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>
