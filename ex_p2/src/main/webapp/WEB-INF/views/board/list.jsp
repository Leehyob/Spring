<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board List</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				DataTables Advanced Tables
				<button id="regBtn" type="button"
					class="btn btn-xs btn-primary pull-right">Register New
					Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성 날짜</th>
							<th>수정 날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}"></c:out></td>
								<td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
										<c:out value="${board.title}"></c:out>
								</a></td>
								<td><c:out value="${board.writer}"></c:out></td>
								<td><fmt:formatDate value="${board.regdate}"
										pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
								<td><fmt:formatDate value="${board.updateData}"
										pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
							</tr>
						</c:forEach>
				</table>
				<!-- /.table-responsive -->
				<!-- The Modal -->
				<div class="modal fade" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Modal Title</h4>
							</div>

							<!-- Modal body -->
							<div class="modal-body">처리가 완료되었습니다.</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>

				<!-- 페이징 처리 -->
				<div class="container" style="text-align: center;">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.startPage-1}">Previous</a></li>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
							<li class="page-item ${pageMaker.cri.pageNum == num ? 'active' : ''}">
							<a class="page-link"  href="${num}">${num}</a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next}">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.endPage+1}">Next</a></li>
						</c:if>
					</ul>
				</div>
				<!-- 페이징 처리 마지막 -->
				
				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				</form>

			</div>
			<!-- /.col-lg-6 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<script type="text/javascript">
	// 	$(document).ready(function() {
	// 		console.log("1")
	// 		var result = '<c:out value="${result}"/>';

	// 		checkModal(result);
	// 								// 브라우저 제목, url
	// 		//history.replaceState({},null, null);
	// 		// || history.state

	// 		function checkModal(result){
	// 			console.log('2')			
	// 			if(result === ''){
	// 				console.log('asdasd')
	// 				return;
	// 			}
	// 			if(parseInt(result) > 0){
	// 				$(".modal-body").html("게시글" + parseInt(result) + "번이 등록되었습니다.");
	// 			}

	// 			$("#myModal").modal("show");
	// 		}

	// 		$("#regBtn").on("click",function(){
	// 			self.location="/board/register";
	// 		})

	// 	})

	$(document).ready(
			function() {

				var result = '<c:out value="${result}" />';

				checkModal(result);

				// 		history.replaceState({}, null, null);
				//  || history.state

				//모달창
				function checkModal(result) {
					if (result === "") {
						return;
					}
					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글 " + parseInt(result) + "번이 등록되었습니다.");
					}/* else if(result === "modify"){
					$(".modal-body").html("게시글 수정되었습니다.");
					}
					}else if(result === "delete"){
					$(".modal-body").html("게시글 삭제되었습니다.");
					} */
					$("#myModal").modal("show");
				}

				//register 호출
				$("#regBtn").on("click", function() {
					self.location = "/board/register";
				});

				/* document.getElementById("regBtn").addEventListener("click",function(){
					window.location.href = "/board/register";
				}) */

				var actionForm = $("#actionForm");
				
				$(".page-item a").on("click",function(e){
					e.preventDefault();
					
					console.log("click");
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					actionForm.submit();
				})
				
				
			});
	
	
</script>

<%@ include file="../includes/footer.jsp"%>
