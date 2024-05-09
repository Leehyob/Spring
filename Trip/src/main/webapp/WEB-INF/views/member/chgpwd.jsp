<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<form action="/member/chgpwd" method="post" id="pwdForm">
		<c:if test="${not empty member_email}">
		<input type="hidden" name="member_email" value="${member_email}">
			<h3>새 비밀번호 설정</h3>
			<hr>
			<label><b>비밀번호</b></label>
			<input type="password" placeholder="Enter Password" name="pwd">
			<label><b>비밀번호 재입력</b></label>
			<input type="password" placeholder="Repeat Password" name="pwd-repeat">
			<hr>
			<button type="submit" id="submitBtn">변경하기</button>
		</c:if>
	</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#submitBtn").on("click",function(){
			var pwdForm = $("#pwdForm");
			if(pwdForm.find("input[name='pwd']").val()===""){
				alert("변경할 비밀번호를 입력해주세요")
				return false;
			}
			if(pwdForm.find("input[name='pwd-repeat']").val()===""){
				alert("비밀번호를 재입력해주세요")
				return false
			}
			if(pwdForm.find("input[name='pwd']").val().length<9){
				alert("비밀번호는 8자리 이상이어야 합니다.")
				return false;
			}
			if(pwdForm.find("input[name='pwd']").val()!=pwdForm.find("input[name='pwd-repeat']").val()){
				alert("비밀번호가 일치하지 않습니다")
				return false
			}
			alert("비밀번호가 변경되었습니다")
			return true;
		})
	})
</script>
</html>