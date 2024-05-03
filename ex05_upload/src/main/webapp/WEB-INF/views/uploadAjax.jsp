<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload With Ajax</h1>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
</div>
	<button id="uploadBtn">Upload</button>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#uploadBtn").on("click",function(e){
			var formData = new FormData();	//값 전달 형태(html의 form과 같은 역할)
			var inputFile = $("input[name='uploadFile']");
			
			var files = inputFile[0].files;
			
			console.log(files);
			
			for(var i=0; i<files.length; i++){
				formData.append("uploadFile",files[i]);
				
				if(!(checkExtension(files[i].name, files[i].size))){
					return false;
				}
			}
			
			$.ajax({	//값 전달(html의 submit과 같은 역할)
				url : '/uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'post',
				success : function(result){
					console.log("result")
					alert("Uploaded")
				}
			})
			})//uploadBtn end
									//모든 문자.확장자가 밑의 타입이면 업로드 할 수 없음
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880 //5MB
			
			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과")
					return false;
				}
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
		})
	</script>
</body>
</html>