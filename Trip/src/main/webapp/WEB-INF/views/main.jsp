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
<h1>main</h1>
<h2>${member_email}</h2>
<h2>${auth}</h2>
<c:if test="${auth eq '[ROLE_MEMBER]'}">
	<button type="button" id="cash" onclick="location.href='/main/cash'">유료회원 전환</button>
</c:if>
<c:if test="${not empty member_email && member_email ne 'anonymousUser'}">
	<button type="button" onclick="location.href='/customLogout'">로그아웃</button>
</c:if>
	<button type="button" onclick="location.href='https://kauth.kakao.com/oauth/logout?client_id=3334abd3f2359d5a2d50f1d20357c04f&logout_redirect_uri=http://localhost:8181/login/kakaoLogout'">카카오 로그아웃</button>
<c:if test="${member_email eq 'anonymousUser'}">
	<button type="button" onclick="location.href='/customLogin'">로그인</button>
</c:if>
<button type="button" onclick="location.href='/board/register'">게시글 등록</button>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	var member_email = $("#email").val()
	
	$("#cash").on("click",function(){
		if(member_email = ""){
			alert("로그인 후 이용해주세요!")
			self.location = "/customLogin";
			return false;
		}
		return;
	})
	
	
})

</script>
</html>