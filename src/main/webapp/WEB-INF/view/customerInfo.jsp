<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Classification</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form method="GET" action="/search" modelAttribute="customer" cssClass="form-horizontal">

            <c:choose>
                <c:when test="${not empty customer.customerInfo}">
                    <div class="form-group">
                        <h2>Customer Classification</h2>
                        <ul>
                            <c:if test="${customer.customerInfo.personType eq 'PM'}">
                                <li><spring:message code="classification.afternoonPerson"/></li>
                            </c:if>
                            <c:if test="${not customer.customerInfo.fastSpender && customer.customerInfo.bigSpender}">
                                <li><spring:message code="classification.bigSpender"/></li>
                            </c:if>
                            <c:if test="${customer.customerInfo.bigTicketSpender}">
                                <li><spring:message code="classification.bigTicketSpender"/></li>
                            </c:if>
                            <c:if test="${customer.customerInfo.personType eq 'AM'}">
                                <li><spring:message code="classification.morningPerson"/></li>
                            </c:if>
                            <c:if test="${customer.customerInfo.fastSpender && not customer.customerInfo.bigSpender}">
                                <li><spring:message code="classification.fastSpender"/></li>
                            </c:if>
                            <c:if test="${customer.customerInfo.potentialSaver}">
                                <li><spring:message code="classification.potentialSaver"/></li>
                            </c:if>
                            <c:if test="${customer.customerInfo.fastSpender && customer.customerInfo.bigSpender}">
                                <li><spring:message code="classification.potentialLoan"/></li>
                            </c:if>
                        </ul>
                    </div>
                    <div class="form-group">
                        <h2>Customer Balance</h2>
                        <h4>${customer.customerInfo.balance}</h4>
                    </div>
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${not empty customer.customerInfo.transactions}">
                                <h2>Customer Transactions</h2>
                                <table class="table table-bordered">
                                    <tr>
                                        <td><spring:message code="transaction.id"/></td>
                                        <td><spring:message code="transaction.date"/></td>
                                        <td><spring:message code="transaction.amount"/></td>
                                        <td><spring:message code="transaction.description"/></td>
                                    </tr>
                                    <c:forEach var="transaction" items="${customer.customerInfo.transactions}">
                                        <tr>
                                            <td>${transaction.id}</td>
                                            <td>${transaction.date}</td>
                                            <td>${transaction.amount}</td>
                                            <td>${transaction.description}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <spring:message code="transaction.not_found"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <h2><spring:message code="customer.not_found"/></h2>
                    </div>

                </c:otherwise>
            </c:choose>
            <div class="form-group">
                <input type="submit" value="Return" class="btn btn-default"/>
            </div>
        </form>
    </div>
</body>
</html>
