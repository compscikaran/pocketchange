<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Expense</title>
<jsp:include page="imports.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/><br><br>
<div class="container">
	
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<h1>Add New Expense</h1><br><br>

		<form:form action="create" modelAttribute="exp">
			<label for="title">Title</label><br>
			<form:input path="title" /><br><br>
			<label for="amount">Amount</label><br>
			<form:input path="amount" /><br><br>
			<label for="category">Category</label><br>
			<form:select path="category">
    			<form:options items="${categoryList}"></form:options>
			</form:select><br><br>
			<input type="submit" value="Add"/>
			<input type="reset" value="Reset"/>
		</form:form>
	</div>
	<div class="col-md-2"></div>
</div>
</div>
</body>
</html>