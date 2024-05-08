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
<c:if test="${empty member_email}">
	일치하는 아이디가 존재하지 않습니다.
<button type="button" onclick="location.href='/member/findid'">아이디 다시 찾기</button>
</c:if>
<c:if test="${not empty member_email}">
	회원님의 아이디는 ${member_email} 입니다.
<button type="button" onclick="location.href='/customLogin'">로그인하러 가기</button>
</c:if>

</body>
</html>