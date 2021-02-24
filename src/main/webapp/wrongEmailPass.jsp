<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">
                        <fmt:message key="login_email"/>
                    </label>
                    <input type="email" name="user_email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" minlength="5" maxlength="40" required>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">
                        <fmt:message key="login_password"/>
                    </label>
                    <input type="password" name="user_password" class="form-control" id="exampleInputPassword1" minlength="2" maxlength="100" required>
                </div>
                <c:if test="${requestScope['checkUser'] == false}">
                <div class="mb-3 form-check">
                        <div class="input-group mb-3">
                            <div class="badge bg-danger text-wrap" style="width: 20rem;">
                                <fmt:message key="login_incorrect"/>
                            </div>
                        </div>
                </div>
                </c:if>
                <button type="submit" class="btn btn-primary"><fmt:message key="login_sign_in"/></button>
            </form>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>
