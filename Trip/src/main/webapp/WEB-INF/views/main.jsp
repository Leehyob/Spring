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

	<button type="button" onclick="location.href='/customLogout'">로그아웃</button>
	
	<button type="button" onclick="location.href='https://kauth.kakao.com/oauth/logout?client_id=3334abd3f2359d5a2d50f1d20357c04f&logout_redirect_uri=http://localhost:8181/login/kakaoLogout'">카카오 로그아웃</button>
<c:if test="${empty member_email}">
	<button type="button" onclick="location.href='/customLogin'">로그인</button>
</c:if>
</body>
</html>