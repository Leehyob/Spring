<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <h2>비밀번호 찾기</h2>
  <form action="/member/findPwd" method="post">
    <div class="form-group">
      <label>이메일</label>
      <input type="text" class="form-control" id="id" placeholder="이메일을 입력해주세요" name="member_email">
      <label id="label1"></label>
    </div>
			<br><button type="button" class="btn btn-primary" id="email-Check-Btn">인증번호 받기</button>
		<div class="mail-check-box">
		<input class="form-control mail-check-input" placeholder="인증번호 여섯 자리를 입력해주세요!" disabled="disabled" maxlength="6">
		<button type="button" id="phone2">인증하기</button>
			<span id="mail-check-warn"></span>
		</div>
    <button type="submit" class="btn btn-primary" id="submitBtn">비밀번호 변경</button>
  </form>
</div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$('#email-Check-Btn').on("click",function() {
			var email = $('#id').val(); // 이메일 주소값 얻어오기!
			console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
			var checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			
			$.ajax({
				type : 'post',
				url : '/mailCheck', // GET방식이라 Url 뒤에 email을 뭍힐수있다.,
				data :{ email : email},
				dataType : 'json',
				success : function (result) {
					console.log("result : " +  result);
					checkInput.attr('disabled',false);
					code =result;
					alert('인증번호가 전송되었습니다.')
				}			
			}); // end ajax
		}); // end send eamil
		
		$(".mail-check-input").on("focusout", function() {
	    	var inputCode = $(".mail-check-input").val(); //인증번호 입력 칸에 작성한 내용 가져오기
	    	
	    	console.log("입력코드 : " + inputCode);
	    	console.log("인증코드 : " + code);
	    		
	    	if(Number(inputCode) === code){
	        	$("#mail-check-warn").html('인증번호가 일치합니다.');
	        	$("#mail-check-warn").css('color', 'green');
	    		$('#mail-Check-Btn').attr('disabled', true);
	    		$('#id').attr('readonly', true);
	    		$(".signupbtn").attr("disabled", false);
	    	}else{
	        	$("#mail-check-warn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
	        	$("#mail-check-warn").css('color', 'red');
	        	$(".signupbtn").attr("disabled", true);
	    	}
	    });
		
		$("#submitBtn").on("click",function(e){
			var id = $("#id").val();
			console.log(email);
		});//end submit
	})
	
</script>
</html>