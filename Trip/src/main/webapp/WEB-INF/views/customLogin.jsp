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
	<input type="hidden" name="member_email" value="">
		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
		<div class="error">
			${error}
		</div>
		<div class="container">
			<label for="username"><b>Email</b></label> 
			<input type="text" placeholder="Enter Email" name="username"><br>
			<label for="password"><b>Password</b></label> 
			<input type="password" placeholder="Enter Password" name="password"><br> <label>
			<input type="checkbox" checked="checked" name="remember-me">
				Remember me
			</label>
			<br><button type="submit" id="loginBtn">Login</button>
			<button type="button" id="mainBtn" onclick="location.href='/main'">메인으로</button>

		</div>

		<div class="container" style="background-color: #f1f1f1">
			<span class="pwd">Forgot <a href="/member/findid">Email?</a>/<a href="/member/findpwd">password?</a></span>
		</div>
		<hr>
		<div id="social">
		<p style="color:gray;">소셜 로그인</p>
		<a id="kakaoBtn" href="https://kauth.kakao.com/oauth/authorize?client_id=3334abd3f2359d5a2d50f1d20357c04f&redirect_uri=http://localhost:8181/login/kakao&response_type=code">Kakao 로그인</a>
		<hr>
		</div>
	</form>
	<p>아직 아이디가 없으신가요?<a href="/member/agree" >회원가입하러 가기</a></p>
	
	<form id="form-kakao-login" method="post" action="kakao-login">
		<input type="hidden" name="member_email"/>
		<input type="hidden" name="name"/>
		<input type="hidden" name="profile"/>
		    		</form>
	
</body>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
			var loginForm = $("#loginForm");
			
			
			$("#loginBtn").on("click",function(){
				
				if($("input[name='username']").val()===""){
					alert("이메일을 입력하세요");
					return false;
				}
				else if($("input[name='password']").val()===""){
					alert("비밀번호를 입력하세요");
					return false;
				}
				return;
				
			})
			
			
			
		})	
	
	</script>
	

</html>