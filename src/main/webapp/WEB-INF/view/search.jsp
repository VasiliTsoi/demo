<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form:form method="POST" action="/search" modelAttribute="customer" cssClass="form-horizontal">
            <div class="form-group">
                <h2>Customer Search</h2>
            </div>
            <div class="form-group" id="groupId">
                <form:label path="id">Customer Id</form:label>
                <form:input path="id" cssClass="form-control"/>
                <form:errors path="id">
                    <form:errors path="id" cssClass="help-inline"/>
                    <script type="text/javascript">
                     $("#groupId").addClass("has-error");
                    </script>
                </form:errors>
            </div>
            <div class="form-group" id="yearId">
                <form:label path="year">Year</form:label>
                <form:input path="year" cssClass="form-control"/>
                <form:errors path="year">
                    <form:errors path="year" cssClass="help-inline"/>
                    <script type="text/javascript">
                        $("#yearId").addClass("has-error");
                    </script>
                </form:errors>
            </div>
            <div class="form-group" id="monthId">
                <form:label path="month">Month</form:label>
                <form:input path="month" cssClass="form-control"/>
                <form:errors path="month">
                    <form:errors path="month" cssClass="help-inline"/>
                    <script type="text/javascript">
                        $("#monthId").addClass("has-error");
                    </script>
                </form:errors>
            </div>
            <div class="form-group">
                <input type="submit" value="Search" class="btn btn-default"/>
            </div>
        </form:form>
    </div>
</body>
</html>
