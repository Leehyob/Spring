<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="../includes/header.jsp"%>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tables</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Board List Page
					<button id="regBtn" type="button" class="btn btn-xs button-primary pull-right">
						Register New Board</button>
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
								<th>작성일</th>
								<th>수정일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="board">
								<tr>
									<td><c:out value="${board.bno}"></c:out></td>
									<td><a href ='/board/get?bno=<c:out value="${board.bno}"/>'>
									<c:out value="${board.title}"></c:out></a></td>
									<td><c:out value="${board.writer}"></c:out></td>
									<td><fmt:formatDate value="${board.regdate}"
											pattern="yyyy-MM-dd hh:mm:ss" /></td>
									<td><fmt:formatDate value="${board.updateData}"
											pattern="yyyy-MM-dd hh:mm:ss" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- /.table-responsive -->
					<!-- The Modal -->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Modal title</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body">처리가 완료되었습니다.</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Close</button>
								</div>

							</div>
						</div>
					</div>

				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<script>
$(document).ready(function(){
		
		var result = '${result}'
		
		/* var result2 = "<c:out value='${result}'/> "; */
		
		console.log(parseInt(result))
		checkModal(result)
		
		//모달창
		function checkModal(result){
			if(result === '' || history.state){
				return;
			}
			if(parseInt(result)>0){
				$(".modal-body").html("게시글 " + parseInt(result)+"번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
	
		
		//register 호출
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
		
		/* document.getElementById("regBtn").addEventListener("click",function(){
			window.location.href = "/board/register";
		}) */
	</script>

	<%@ include file="../includes/footer.jsp"%>

