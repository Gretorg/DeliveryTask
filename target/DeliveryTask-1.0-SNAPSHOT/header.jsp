<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <fmt:message key="button_home"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <c:if test="${sessionScope['role'] == 2}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/arrangeDelivery">
                                <fmt:message key="header_arrange_delivery"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/myOrders?status=1">
                                <fmt:message key="header_my_request"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/myOrders?status=2">
                                <fmt:message key="header_pay_a_receipt"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/myOrders?status=3">
                                <fmt:message key="header_my_receipt"/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['role'] == 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/showUserOrders?status=1">
                                <fmt:message key="header_customer_requests"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/showUserOrders?status=2">
                                <fmt:message key="header_payment_expected"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/showUserOrdersPagination?page=1">
                                <fmt:message key="header_archive_of_requests"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/showReport">
                                <fmt:message key="header_get_requests"/>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope['role'] != null }">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <c:out value="${sessionScope['email']}"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">
                                <fmt:message key="button_logout"/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope['role'] == null}">
                        <li class="nav-item">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#header_auth-modal">
                                <fmt:message key="header_authorization"/>
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="header_auth-modal" tabindex="-1" aria-labelledby="header_auth-modalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="header_auth-modalLabel">
                                                <fmt:message key="login_sign_in"/>
                                            </h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${pageContext.request.contextPath}/login" method="post">
                                                <div class="mb-3">
                                                    <label for="exampleInputEmail1" class="form-label">
                                                        <fmt:message key="login_email"/>
                                                    </label>
                                                    <input type="email" name="user_email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="exampleInputPassword1" class="form-label">
                                                        <fmt:message key="login_password"/>
                                                    </label>
                                                    <input type="password" name="user_password" class="form-control" id="exampleInputPassword1">
                                                </div>
                                                <div class="mb-3">
                                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/newUser.jsp">
                                                        <fmt:message key="button_new_user"/>
                                                    </a>
                                                </div>
                                                <button type="submit" class="btn btn-primary">
                                                    <fmt:message key="button_submit"/>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="button_language"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu" aria-labelledby="navbarDarkDropdownMenuLink">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/changeLanguage?lang=en">English</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/changeLanguage?lang=ru">Русский</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>