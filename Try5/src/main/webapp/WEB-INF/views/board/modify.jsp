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
					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
					<button type="submit" data-oper='remove' class="btn btn-danger" >Remove</button>
					<button type="submit" data-oper='list' class="btn btn-info" >List</button>
					
					</form>					
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	$(document).ready(function(){
		var form = $("form");
		
		$("button").on("click",function(e){
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			if(operation === 'remove'){
				form.attr("action","/board/remove");
			}
			else if(operation === 'list'){
				form.attr("action","/board/list").attr("method","get");
				
				var pageNumT = $("input[name='pageNum']").clone();
				var amountT = $("input[name='amount']").clone();
				var typeT = $("input[name='type']").clone();
				var keywordT = $("input[name='keyword']").clone();
				
				form.empty();
				
				form.append(pageNumT);
				form.append(amountT);
				form.append(typeT);
				form.append(keywordT);
				
			}
				form.submit();
		})
		
	})
</script>
<%@ include file="../includes/footer.jsp" %>