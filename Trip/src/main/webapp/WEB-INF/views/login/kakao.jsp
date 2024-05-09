<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="id01" class="modal">
  <form class="modal-content" action="/login/kakao" method="post">
    <div class="container">
      <h1>회원가입</h1>
      <hr>
      <label><b>이메일</b></label>
      <input type="text" id="id" name="member_email" value="${email}" autofocus="autofocus" readonly="readonly"> 
		<hr>
      <label><b>이름</b></label>
      <input type="text" value="${name}" name="name" readonly="readonly">
      <hr>
      <label><b>휴대폰 번호</b></label>
      <input type="text" placeholder="Enter Phone Number" name="phone" required>
      <hr>

      <div class="clearfix">
        <button type="submit" class="signupbtn">Sign Up</button>
      </div>
    </div>
  </form>
</div>


</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$(".signupbtn").on("click",function(){
			var joinForm = $(".modal-content");
			
			
			if(joinForm.find("input[name='phone']").val()===""){
				alert("휴대폰 번호를 입력해주세요")
				return false;
			}
			alert("회원가입이 완료되었습니다!");
			return;
			
		})
		

	
</script>
</html>