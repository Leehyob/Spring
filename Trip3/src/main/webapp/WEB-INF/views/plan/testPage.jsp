<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${info.areaname}
<input type="hidden" name="info" value="${info}">
<input type="text" name="dayCnt" value="6">
	<div class="inputBtn"></div>
	<div class="seeInfo"></div>
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		updateBtn();
		
		function updateBtn(){
			var dayCnt = $("input[name='dayCnt']").val();
			console.log(dayCnt);
				var str = "";
			for(var i=1; i<= dayCnt; i++){
				str += "<button class='day'>"
				str += i+"일차"
				str += "</button><br>"
				console.log(i);
				
			}
				$(".inputBtn").html(str);
				$(".day").on("click",function(){
					var day = $(this).text();
					console.log(day);
					var str2 ="";
						str2 += "<h2>"+day+"</h2>"
						str2 += "<br><p>" + info + "</p>"
						$(".seeInfo").html(str2);
				})
		}
		
		var info = $("input[name='info']").val();
		
		console.log(info);
		
		
		
	})
</script>

</html>
