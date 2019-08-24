<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<jsp:include page="imports.jsp"/>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="header.jsp"/><br><br>
<div class="container">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
    	<h1 style="text-align:center;">Category Analysis</h1><br><br>
    	<canvas id="categories"></canvas>
    	</div>
		<div class="col-md-2"></div>
	</div>
	<br><br><hr><br>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
    	<h1 style="text-align:center;">Monthly Analysis</h1><br><br>
    	<canvas id="monthly"></canvas>
    	</div>
		<div class="col-md-2"></div>
	</div>
	<br><br><hr><br>
</div>
<!-- Loading the Category Spending Chart -->
<script>
    datacats = {
    	    datasets: [{
    	        label: "Category Wise Spending",
    	        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850", "#99b433", "#e3a21a"],
    	    	data: ${model.getCategorySpending()}
    	    }],
    	    // These labels appear in the legend and in the tooltips when hovering different arcs
    	    labels: ["SHOPPING", "OTHERS", "HEALTH", "TRAVEL", "BILLS", "EMI", "FOOD"]
    
    	};
        
        var ctx1= document.getElementById("categories").getContext("2d");
        
        var myDoughnutChart1 = new Chart(ctx1, {
            type: 'doughnut',
            data: datacats,
            options: Chart.defaults.doughnut
        });
</script>
<!-- Loading Monthly Spending Chart -->    
<script>
    datamonths = {
    	    datasets: [{
    	        label: "Monthly Spending",
    	        backgroundColor: "#3e95cd",
    	    	data: ${model.getMonthlySpending()}
    	    }],
    	    // These labels appear in the legend and in the tooltips when hovering different arcs
    	    labels: ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"]
    
    	};
    
    var ctx2= document.getElementById("monthly").getContext("2d");


    var myDoughnutChart2 = new Chart(ctx2, {
        type: 'bar',
        data: datamonths,
        options: Chart.defaults.bar
    });    
</script>	
</body>
</html>