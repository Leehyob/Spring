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
  <form class="modal-content" action="/member/join" method="post">
    <div class="container">
      <h1>회원가입</h1>
      <hr>
      <label><b>이메일</b></label>
      <input type="text" placeholder="Enter Email" id="id" name="member_email" autofocus="autofocus"> 
		<label id="label1"></label>
			<br><button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
		<div class="mail-check-box">
		<input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
			<span id="mail-check-warn"></span>
		</div>
		<hr>
      <label><b>비밀번호</b></label>
      <input type="password" placeholder="Enter Password" name="pwd" >
	<hr>
      <label><b>비밀번호 재입력</b></label>
      <input type="password" placeholder="Repeat Password" name="pwd-repeat" >
      <hr>
      <label><b>이름</b></label>
      <input type="text" placeholder="Enter Username" name="name" required>
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
			
			if(!joinForm.find("input[name='member_email']").val()){
				alert("이메일을 입력하세요");
				return false;
			}
			 if(!joinForm.find("input[name='member_email']").val().match('@')){
				alert("이메일 형식이 올바르지 않습니다!")
				return false;
			} 
			 if(!joinForm.find("#emailCheckBtn")){
					alert("이메일을 입력하세요");
					return false;
				}
			if(!joinForm.find("input[name='pwd']").val()){
					alert("비밀번호를 입력하세요");
					return false;
				}
			if(joinForm.find("input[name='pwd']").val().length < 8){
				alert("비밀번호는 8자리 이상으로 설정해야 합니다")
				return false;
			}
			if(joinForm.find("input[name='pwd']").val() != joinForm.find("input[name='pwd-repeat']").val()){
				alert("비밀번호가 일치하지 않습니다")
				return false;
			}
			if(joinForm.find("input[name='name']").val()===""){
				alert("이름을 입력해주세요")
				return false;
			}
			if(joinForm.find("input[name='phone']").val()===""){
				alert("휴대폰 번호를 입력해주세요")
				return false;
			}
			
		})
		
$("#id").on("focusout", function() {
    		
    		var id = $("#id").val();
    		
    		if(id === ''|| id.length === 0) {
    			$("#label1").css("color", "red").text("공백은 ID로 사용할 수 없습니다.");
    			return false;
    		}
    		
        	//Ajax로 전송
        	$.ajax({
        		url : '/ConfirmId',
        		data : {
        			id : id
        		},
        		type : 'post',
        		dataType : 'json',
        		success : function(result) {
        			if (result === true) {
        				$("#label1").css("color", "black").text("사용 가능한 ID 입니다.");
        			} else{
        				$("#label1").css("color", "red").text("이미 존재하는 ID 입니다.");
        				$("#id").val('');
        			}
        		}
        	}); //End Ajax
    	});
		
		$('#mail-Check-Btn').on("click",function() {
			const email = $('#id').val(); // 이메일 주소값 얻어오기!
			console.log('완성된 이메일 : ' + eamil); // 이메일 오는지 확인
			const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			
			$.ajax({
				type : 'get',
				url : '<c:url value ="/user/mailCheck?email="/>'+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
				success : function (data) {
					console.log("data : " +  data);
					checkInput.attr('disabled',false);
					code =data;
					alert('인증번호가 전송되었습니다.')
				}			
			}); // end ajax
		}); // end send eamil
	})
	
</script>
</html>