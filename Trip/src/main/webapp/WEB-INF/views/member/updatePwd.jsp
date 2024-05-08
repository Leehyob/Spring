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
</c:if>
<c:if test="${not empty member_email}">
	<h3>새 비밀번호 설정</h3>
      <hr>
	<label><b>비밀번호</b></label>
      <input type="password" placeholder="Enter Password" name="pwd" >
      <label><b>비밀번호 재입력</b></label>
      <input type="password" placeholder="Repeat Password" name="pwd-repeat" >
	<hr>
</c:if>
</body>
</html>