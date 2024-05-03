<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			
			<div class="panel-heading">Board Read</div>
			
			<div class="panel-body">
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name="bno"
						value='<c:out value="${board.bno}"/>' readonly="readonly">
					</div>					
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name="title"
						value='<c:out value="${board.title}"/>' readonly="readonly">
					</div>					
					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" row="3" name="content"
						readonly="readonly"><c:out value="${board.content}"/></textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input class="form-control" name="writer"
						value='<c:out value="${board.writer}"/>' readonly="readonly">
					</div>					
					<button data-oper='modify' class="btn btn-default">Modify</button>
					<button data-oper='list' class="btn btn-info" >List</button>
					
					<form id='operForm' action="/board/modify" method="get">
						<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
						<input type="hidden" name="pageNum" value="${cri.pageNum}">
						<input type="hidden" name="amount" value="${cri.amount}">	
						<input type="hidden" name="type" value="${cri.type}">
						<input type="hidden" name="keyword" value="${cri.keyword}">					
					</form>					
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			
			<div class="panel-heading">
				<i class="fa fa-comments fa-fa"></i>Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
			</div>
			<div class="panel-body">
					<ul class="chat">
						<li class="Left clearfix" data-rno='12'>
							<div>
								<div class="header">
									<strong class="primary-font">user00</strong>
									<small class="pull-right text-muted">2024-04-22 14:12:00</small>
								</div>
							</div>
							<p>Good Job!
						</li>
					</ul>
			</div>
					<!-- 댓글 페이징 처리 -->
					<div class="panel-footer">
					</div>
		</div>
	</div>
</div>

<!--  댓글 모달창 -->
<div class="container">
<!--   <h2>Modal Example</h2>
  Button to Open the Modal
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    Open modal
  </button> -->

  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">REPLY MODAL</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<div class="form-group">
        		<label>Reply</label>
        		<input class="form-control" name="reply" value="new reply">
        	</div>
        	<div class="form-group">
        		<label>Replyer</label>
        		<input class="form-control" name="replyer" value="new replyer">
        	</div>
        	<div class="form-group">
        		<label>Reply Date</label>
        		<input class="form-control" name="replyDate" value="">
        	</div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button id="modalRegisterBtn" type="button" class="btn btn-primary" data-dismiss="modal">Register</button>
          <button id="modalModBtn" type="button" class="btn btn-warning" data-dismiss="modal">Modify</button>
          <button id="modalRemoveBtn" type="button" class="btn btn-danger" data-dismiss="modal">Remove</button>
          <button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  
</div>
<!-- 댓글 모달창 끝 -->

<style>
	.chat > li:hover{
		cussor:pointer;
	}
</style>

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>
$(document).ready(function(){
	
	console.log("JS TEST................")
	
	var bnoValue = "${board.bno}";
	var replyUL = $(".chat")
	
	console.log("bnoValue................" + bnoValue)
	
	showList(1);
	
	function showList(page){
		replyService.getList({bno:bnoValue, page:page}, function(replyCnt, list){
			
			
			//마지막 페이지 처리
			if(page == -1){
				var pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum)
				return
			}
			
			var str=""
			
			if(list == null || list.length==0){
				replyUL.html("")
				return ;
			}
			
			for(var i=0; i<list.length; i++){
				str += "<li class='left clearfix' data-rno='"+ list[i].rno  +"'>"
    			str += "<div>"
    			str += "<div class='header'>"
    			str += "<strong class='primary-font'>"+ list[i].replyer+"</strong>"		
    			str += "<small class='pull-right text-muted'> "+ replyService.displayTime(list[i].replyDate) +" </small>"		
    			str += "</div>"	
    			str += "</div>"
    			str += "<p>"+list[i].reply +"</p></li>"
			}
			replyUL.html(str)
			
			showReplyPage(replyCnt)
			
		}) 
	}	//End showList
		
	var modal = $(".modal")
	var modalInputReply = modal.find("input[name='reply']")
	var modalInputReplyer = modal.find("input[name='replyer']")
	var modalInputReplyDate = modal.find("input[name='replyDate']")
	
	var modalRegisterBtn = $("#modalRegisterBtn");
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalCloseBtn = $("#modalCloseBtn")
		
		
	$("#addReplyBtn").on("click", function(e){
		
		modal.find("input").val("")
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide()
		modalRegisterBtn.show()
		
		$(".modal").modal("show");
	});
		
	
	//댓글 등록
	modalRegisterBtn.on("click", function(e){
		
		console.log("register...............")
	
		var reply = {
			reply : modalInputReply.val(),
			replyer: modalInputReplyer.val(),
			bno : bnoValue
		}
		
		replyService.add(reply, function(result){
			alert(result)
			modal.find("input").val()
			modal.modal("hide")
			
			showList(-1)
		})
		
	}) // End 댓글 등록
		
	//이벤트 위임을 통한 댓글 클릭 이벤트
	$(".chat").on("click","li", function(e){
		
		console.log("get...............")
		var rno = $(this).data("rno")
		
		replyService.get(rno, function(reply){
			modalInputReply.val(reply.reply)
			modalInputReplyer.val(reply.replyer)
			modalInputReplyDate.val(  replyService.displayTime(reply.replyDate)).attr("readonly", "readonly")
			
			modal.data("rno", reply.rno)
			
			modal.find("button[id != 'modalCloseBtn']").hide()
			modalModBtn.show()
			modalRemoveBtn.show()
			
			$(".modal").modal("show")
			
		})			
	})  //이벤트 위임
	
	//댓글 수정
	modalModBtn.on("click", function(e){
		console.log("update...............")
		
		var reply = {
			reply: 	modalInputReply.val(),
			rno: modal.data("rno")
		}
		
		replyService.update(reply, function(result){
			alert(result);
			modal.modal("hide")
			showList(1)
		})
	})  //End 댓글 수정

	//댓글 삭제
	modalRemoveBtn.on("click", function(e){
		console.log("remove...............")
		
		let rno =  modal.data("rno")
		
		console.log(rno)
		replyService.remove(rno, function(result){
			alert(result);
			modal.modal("hide")
			showList(1)
		})
	})  //End 댓글 삭제
	
	
	var pageNum = 1
	var replyPageFooter = $(".panel-footer")
	
	function showReplyPage(replyCnt){
		
		var endNum = Math.ceil(pageNum / 10.0) * 10
		var startNum = endNum - 9
		
		var prev = startNum != 1
		var next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0)
		}
		
		if(endNum * 10 < replyCnt){
			next = true
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='"+ (startNum-1) +"'>이전</a></li>";
		}
		
		for(var i=startNum; i<=endNum; i++){
			var active  = pageNum ==i? "active": "";
			
			str += "<li class='page-item "+ active +" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
					
		}
		
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+ (endNum+1) +"'>다음</a></li>";
		}
		str += "</ul>"
		
		replyPageFooter.html(str)
		
	
	replyPageFooter.on("click", "li a", function(e){
        e.preventDefault();
        
        var targetPageNum = $(this).attr("href")
        
        pageNum = targetPageNum
        
        showList(pageNum);
     })

	}
	
	
	
	
	
	
	
	
	
	
	
	//댓글 등록
/* 	replyService.add(		
		
		{reply:"JS Test2", replyer: "tester2", bno:bnoValue},
		
		function(result){
			console.log("complete")
			alert("RESULT : " + result)
		}
	); */
	
	/* replyService.getList(
		{bno:bnoValue, page:1},
		
		function(list){
			//for(var i=0, len=list.length||0; i<len; i++){
			for(var i=0; i<list.length; i++){	
				console.log(list[i])
			}
				
		}
		
	) */
	
	/* var rno = 208
	replyService.remove(rno, function(msg){
			  alert(msg)			
		}
	);
	 */
	
	/* var data = {
				rno:207,
				reply: "Modified Reply..."
	}
	replyService.update(
		data, 
		function(result){
			alert(result);
		}			
	) */
	
	/* replyService.get( 207, function(data){
								console.log(data)
							}
	); */
	
	
	
});
</script>



<script>
 	$(document).ready(function(){
 		
 		let operForm = $("#operForm");
 		
 		$("button[data-oper='modify']").on("click",function(e){
 			operForm.submit();
 		})

 		$("button[data-oper='list']").on("click",function(e){
 			operForm.find("#bno").remove();
 			operForm.attr("action", "/board/list")
 			operForm.submit();
 		})
 		
 	});
 </script>


<%@include file="../includes/footer.jsp" %>      