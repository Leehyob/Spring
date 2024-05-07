<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>login</h1>
	<form id="loginForm" action="/login" method="post">
		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
		<div class="error">
			${error}
		</div>
		<div class="container">
			<label for="username"><b>Email</b></label> 
			<input type="text" placeholder="Enter Email" name="username"><br>
			<label for="password"><b>Password</b></label> 
			<input type="password" placeholder="Enter Password" name="password"><br> <label>
			<input type="checkbox" checked="checked" name="remember">
				Remember me
			</label>
			<br><button type="submit" id="loginBtn">Login</button>

		</div>

		<div class="container" style="background-color: #f1f1f1">
			<span class="pwd">Forgot <a href="#">Email?</a>/<a href="#">password?</a></span>
		</div>
		<hr>
		<div id="social">
		<p style="color:gray;">소셜 로그인</p>
		<a id="kakaoBtn" href="https://kauth.kakao.com/oauth/authorize?client_id=3334abd3f2359d5a2d50f1d20357c04f&redirect_uri=http://localhost:8181/login/kakao&response_type=code">Kakao 로그인</a>
		<hr>
		</div>
	</form>
	<p>아직 아이디가 없으신가요?<button id="goJoin">회원가입하러 가기</button></p>
	
	<form id="form-kakao-login" method="post" action="kakao-login">
		<input type="hidden" name="member_email"/>
		<input type="hidden" name="name"/>
		<input type="hidden" name="profile"/>
		    		</form>
	
</body>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
			$("#goJoin").on("click",function(e){
				self.location = '/member/join';
			})
			
			var loginForm = $("#loginForm");
			
			var username = $("input[name='username']").clone();
			var password = $("input[name='password']").clone();
			
			$("#loginBtn").on("click",function(e){
				if(username===null){
					alert("이메일을 입력하세요");
					return;
				}
				if(password===null){
					alert("비밀번호를 입력하세요");
					return;
				}
				
			})
			
		})	
	
	</script>
	

</html>