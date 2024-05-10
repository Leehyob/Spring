<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" id="email" value="${member_email}">
	<h1>유료회원 전환</h1>
	<p>결제하시겠습니까?</p>
	<button id="payment">결제하기</button>
	<button id="mainBtn" onclick="location.href='/main'">메인으로</button>
</body>
	
	<script>
		$(document).ready(function(){
			var IMP = window.IMP;
			IMP.init("imp37421850")
			
			var member_email = $("#email").val();
			console.log(member_email);
			$("#payment").on("click",function(){
				console.log(member_email);
				requestPay()
				return;
			})
			
			function requestPay(){
				 IMP.request_pay({ // param
			          pg: "html5_inicis",
			          pay_method: "card",
			          name: "유료회원 등록",
			          amount: 10,
			          buyer_email: member_email,
			      }, function (rsp) { // callback
			          if (rsp.success) {
			             $.ajax({
			            	type: 'POST',
			            	url: "/main/cash",
			            	data: {member_email : member_email},  
			            	}).done(function(data){
			            		alert("유료회원으로 전환되었습니다!")
			            	}).fail(function(){
			            		alert("서버 오류가 발생했습니다.")
			            	})
			          }else{
			        	  alert("결제가 취소되었습니다.");
			          }
			      });
			}
		})
	</script>
</html>