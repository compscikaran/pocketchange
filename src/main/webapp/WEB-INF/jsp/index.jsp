<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PocketChange</title>
<jsp:include page="imports.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/><br><br>
<div class="container">
<div class="row">
	<div class="col-md-12">
		<h1 style="text-align:center;">${title}</h1><br><br>	
	</div>
</div>
<c:if test="${filtered == false}">
<div class="row">
	<div class="col-md-12">
	 	<form:form action="filter" modelAttribute="fmodel">
	 		<form:select path="category">
    			<form:options items="${categoryList}"></form:options>
			</form:select>
			<input type="submit" value="Filter"/>
	 	</form:form>
	</div>
</div>
<br><br>
</c:if>
<div class="row">
	<table class="table table-hover table-striped">
		<thead>
			<tr>
			<th>Title</th>
			<th>Amount</th>
			<c:if test="${filtered == false}">
			<th>Category</th>
			</c:if>
			<th>Date</th>
			<th></th>
			<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${elist}" var="item">
    		<tr>
    			<td>${item.title}</td>
    			<td>${item.amount}</td>
    			<c:if test="${filtered == false}">
    			<td>${item.category}</td>
    			</c:if>
    			<td>${item.stamp}</td>
    			<td>
    				<form action="update" method="GET">
    					<input type="hidden" value="${item.expid}" name="id"/>
    					<input type="submit" value="Update"/>
    				</form>
    			</td>
    			<td>
    				<form action="delete" method="DELETE">
    					<input type="hidden" value="${item.expid}" name="id"/>
    					<input type="submit" value="Delete"/>
    				</form>
    			</td>
    		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="row">
	<div class="col-md-2">
		<h5>Total</h5>
	</div>
	<div class="col-md-8"></div>
	<div class="col-md-2">
		<h5><c:out value="${total}"></c:out></h5>
	</div>
</div>
</div>
</body>
</html>