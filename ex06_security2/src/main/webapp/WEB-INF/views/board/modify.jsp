<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify Page</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			
			<div class="panel-heading">Board Modify Page</div>
			
			<div class="panel-body">
			<form role="form" action="/board/modify" method="post">
				<input type="hidden" name="pageNum" value="${cri.pageNum}">
				<input type="hidden" name="amount" value="${cri.amount}">		
				<input type="hidden" name="type" value="${cri.type}">
				<input type="hidden" name="keyword" value="${cri.keyword}">	
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name="bno"
						value='<c:out value="${board.bno}"/>' readonly="readonly">
					</div>					
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name="title"
						value='<c:out value="${board.title}"/>' >
					</div>					
					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" row="3" name="content"
						><c:out value="${board.content}"/></textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input class="form-control" name="writer"
						value='<c:out value="${board.writer}"/>' readonly="readonly">
					</div>					
					<div class="form-group">
						<label>RegDate</label>
						<input class="form-control" name="regdate"
						value='<fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd hh:mm:ss"/>' readonly="readonly">
					</div>					
					<div class="form-group">
						<label>Update Date</label>
						<input class="form-control" name="updateData"
						value='<fmt:formatDate value="${board.updateData}" pattern="yyyy/MM/dd hh:mm:ss"/>' readonly="readonly">
					</div>				
					
					<sec:authentication property="principal" var="pinfo"/>
					<sec:authorize access="isAuthenticated()">	
						<c:if test="${pinfo.username eq board.writer}">
							<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
							<button type="submit" data-oper='remove' class="btn btn-danger" >Remove</button>
						</c:if>
					</sec:authorize>
					<button type="submit" data-oper='list' class="btn btn-info" >List</button>
					
					</form>					
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	$(function(){ 	//(document).ready 생략해도 동작함
		
		
		var formObj = $("form");
	
// 		history.replaceState({},null,null);
		
		$('button').on("click", function(e){
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove'){
				formObj.attr("action","/board/remove");
			}else if(operation === 'list'){
				
				formObj.attr("action","/board/list").attr("method","get");

				var pageNumTag =$("input[name='pageNum']").clone();
				var amountTag =$("input[name='amount']").clone();
				var typeTag = $("input[name='type']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				
				formObj.empty();
				
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(typeTag);
				formObj.append(keywordTag);
				
			}
			
			formObj.submit();
		})	//click 이외에도 radio, checkbox 등 넓은 범위에서 사용
			
		
		// $('button').click(function(e){  -> CLICK만 할 때는 on 사용 대신 이렇게도 표현 가능 
		
	})
	
</script>

<%@ include file="../includes/footer.jsp" %>