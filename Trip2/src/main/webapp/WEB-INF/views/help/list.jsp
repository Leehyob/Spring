<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../includes/header.jsp" %>
    <html lang="en">
    <head>
    <link href="../resources/css/help.css" rel="stylesheet"> 
    </head>
    <body>
<div class="row">
<div class="image_head">
<img id="helpImage" alt="문의사항 섹션" src="../resources/image/help.jpg" >
<!-- <h1 class="imagetext">문의 게시판</h1> -->
</div>
    <div class="col-lg-12">
        <p class="page-header_h">문의 사항&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row" id="help_table">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
              <p>새로운 문의를 남겨보세요</p>
                <button id="regBtn" type="button" class="btn btn-xs pull-right btn-info">문의하기</button>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" id="help_table_r" class="table table-striped table-bordered table-hover" >
                    <thead>
                        <tr>
                            <th>#번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="help" items="${list}">
	                        <tr class="odd gradeX">
	                            <td>${help.help_id }</td>
	                            <td>
	                            	<a class="move" href='${help.help_id}'> ${help.title }</a>
	                            </td>
	                            <td>${help.member_email } </td>
	                            <td><fmt:formatDate value="${help.regDate }" pattern="yyyy-MM-dd"  /></td>
	                            <td><fmt:formatDate value="${help.updateDate }" pattern="yyyy-MM-dd"  /></td>
	                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

                
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
 
 

 
      </body>
 
 <script>
 	$(document).ready(function(){
 		let result = '${result}';
 		
 		checkModal(result);
 		
 		history.replaceState({},null,null);
 		
 		function checkModal(result){
 			
 			if(result === '' || history.state){
 				return ;
 			}
 			
 			if(parseInt(result) > 0){
 				$(".modal-body").html("게시글 " + parseInt(result) + "번 등록 성공 했습니다.")
 			}
 			
 			$("#myModal").modal("show");	
 		};
 		
 		
 		$("#regBtn").on("click",function(){
 			self.location = "/board/register";
 		})
 		
 		
 		let actionForm = $("#actionForm");
 		$(".page-item a").on("click", function(e){
 			e.preventDefault();
 			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
 			actionForm.submit();
 		}) 
 		
 		
 		/*<form id="actionForm" action="/board/get" method="get">
			<input type="hidden" name="pageNum" value=${pageMaker.cri.pageNum}>
			<input type="hidden" name="amount" value=${pageMaker.cri.amount}>
			<input type='hidden' name='bno' value='"+ $(this).attr("href") + "'>
		</form>*/
 		
 		$(".move").on("click",function(e){
 			e.preventDefault();
 			actionForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href") + "'>")
 			actionForm.attr("action", "/board/get");
 			actionForm.submit();
 		})
 		
 		
 		//검색조건 이벤트 처리
 		
 		let searchForm  = $("#searchFrom");
		
		$("#searchFrom button").on("click", function(e){
			e.preventDefault();
			
			if(!searchForm.find("option:selected").val()){
				alert("검색종류를 선택하세요");
				return false;
			}
			
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하세요.");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			
			searchForm.submit();
		})
 	});
 </script>
 
 <%@include file="../includes/footer.jsp" %>      
 
 </html>
      


