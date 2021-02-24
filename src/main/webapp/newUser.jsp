<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./header.jsp" %>
<div class="homePage container-fluid">
    <div class="row">
        <div class="col-12 d-flex justify-content-center align-items-center">
            <form action="${pageContext.request.contextPath}/newUser" method="post">
                <h3><fmt:message key="login_email"/></h3>
                <div class="input-group mb-3">
                    <input type="email" name="user_email" class="form-control" placeholder="email@example.com" minlength="5" maxlength="40" required>
                </div>
                <c:if test="${requestScope['checkEmail'] == false}">
                <div class="input-group mb-3">
                    <div class="badge bg-danger text-wrap" style="width: 20rem;">
                        <fmt:message key="login_msg"/>
                    </div>
                </div>
                </c:if>
                <h3><fmt:message key="login_password"/></h3>
                <div class="input-group mb-3">
                    <input type="password" name="user_password" class="form-control" placeholder="<fmt:message key="login_password"/>" minlength="2" maxlength="100" required>
                </div>
                <h3><fmt:message key="login_name"/></h3>
                <div class="input-group mb-3">
                    <input type="text" name="first_name" class="form-control" placeholder="<fmt:message key="login_name"/>" minlength="1" maxlength="20" required>
                </div>
                <h3><fmt:message key="login_surname"/></h3>
                <div class="input-group mb-3">
                    <input type="text" name="last_name" class="form-control" placeholder="<fmt:message key="login_surname"/>" minlength="1" maxlength="40" required>
                </div>
                <h3><fmt:message key="login_birth_date"/></h3>
                <div class="input-group mb-3">
                    <input type="date" name="birth_date" class="form-control" placeholder="<fmt:message key="login_birth_date"/>" required>
                </div>
                <div class="input-group">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="login_sign_up"/>
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>
<%@ include file="./footer.jsp" %>