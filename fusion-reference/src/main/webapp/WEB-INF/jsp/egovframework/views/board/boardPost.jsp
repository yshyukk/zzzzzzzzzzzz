<%
/*********************************************************
 * 업 무 명 : 게시판 컨트롤러
 * 설 명 : 게시판을 조회하는 화면에서 사용 
 * 작 성 자 : 김민규
 * 작 성 일 : 2022.10.06
 * 관련테이블 : 
 * Copyright ⓒ fusionsoft.co.kr
 *
 *********************************************************/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>퓨전 게시판(상세보기)</title>
</head>
<body>
	<main class="mt-5 pt-5">
	<div class="container-fluid px-4">
		<h1 class="mt-4">게시글 조회</h1>
		<div class="card mb-4">
			<div class="card-body">
				<div class="mb-3">
					<label for="title" class="form-label">제목</label>
					<input type="text" class="form-control" id="title" name="title" value="${boardPost.title}" readOnly>
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용</label>
					<textarea class="form-control" id="content" name="content" readOnly>${boardPost.content}</textarea>
				</div>
				<div class="mb-3">
					<label for="writer" class="form-label">작성자</label>
					<input type="text" class="form-control" id="writer" name="writer" value="${boardPost.writer}" disabled>
				</div>
				<button class="btn btn-outline-secondary" id="move_list">목록</button>
				
					<button onclick="openAnswerForm(${boardPost.board_id})" class="btn btn-outline-danger">답글</button>
				<c:if test="${id eq boardPost.writer}">	
					<button id="move_update" class="btn btn-outline-warning" onclick="location.href='/board/boardPostModify.do?board_id=${boardPost.board_id}'">수정</button>
					<button class="btn btn-outline-danger" onclick="del_board(this, ${boardPost.board_id})">삭제</button>
				</c:if>
			</div>
		</div>
	</div>
	<div class="card-body-2">
		<div class="writeBox">
			<button class="comm_save">저장</button>
			<textarea type="text" class="form-control" id="commnets" name="commnets" placeholder ="최대 2000자까지 작성")></textarea>
		</div>
		<div class="comm_list" >
			<c:forEach var="commList" items="${commList }">
				<div >
					<c:forEach var="i" begin="1" end="${commList.level}">
						<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
					</c:forEach>
					<div class="mb-3" style="display:inline-block; width:80%; border:2px solid lightgrey;">		
						<button class="write_comm">댓글쓰기</button>
						<c:if test="${id eq boardPost.writer}">	
						<button class="comm_update" onclick="comm_upd(this, ${commList.comm_id})">수정</button>
						<button class="comm_delete" onclick="comm_del(this, ${commList.comm_id})">삭제</button>
						</c:if>
						<div class="level" style="display:none;">${commList.level}</div>
						<div class="parent" style="display:none;">${commList.comm_parent }</div>
						<p class="comm_writer">작성자 : ${commList.comm_writer }</p>
						<p class="registDt">${commList.comm_regist_dt }</p>
						<textarea type="text" class="form-control" id="commnets" name="commnets">${commList.comm_content }</textarea>
						<div class="writeBox" style="display:none">
							<div class="hidden">
							<div class="commId" style="display:none;">${commList.comm_id}</div>
							<textarea type="text" class="form-control" id="commnets" name="commnets"></textarea>
							<button class="comm_save2" onclick="comm_info(this, ${commList.comm_id})">저장</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</main>
	
</body>
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript">
	//목록으로 이동/board/boardList.do')
	
	$('#move_list').click(function(){
		opener.document.location.reload();
		self.close();
	})
	// 댓글 등록
	function comm_info(obj, commId){

		var content = $(obj).prev('textarea').val();
		var commParent = commId;
		const urlParams = new URL(location.href).searchParams;
		const boardId = urlParams.get('board_id');
		
		if(content == ""){
			alert("내용을 입력해주세요")
			return false;
		}
		if(content.length >= 2000){
			alert("2000자 이상 등록 할 수 없습니다.")
			return false;
		}
		var formData={
					"comm_content" : content,
					"comm_parent" : commParent,
					"board_id" : boardId
				};
		$.ajax({
  	      type  : "POST",
  	      url    : '/board/commdPost.do',
  	      data: formData,
  	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
  	      success: function(result){
  	    	console.log(result);
  	    	location.reload();	
  	      },
  	      error: function(xhr, status, error) {
  	      	alert("통신실패");
  	      }  
  	   })
	}
	// 댓글 수정
	function comm_upd(obj, commId){
	
		$(obj).siblings('textarea');
		
		var content = $(obj).siblings('textarea').val();
		var commId = commId;
		const urlParams = new URL(location.href).searchParams;
		const boardId = urlParams.get('board_id');
		let upData={
					"comm_content" : content,
					"comm_id" : commId,
					"board_id" : boardId
					};
		$.ajax({
  	      type  : "POST",
  	      url    : '/board/commdUpdate.do',
  	      data: upData,
  	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
  	      success: function(result){
  	    	console.log(result);
  			if(result=="confirm"){
  				alert("수정이 완료되었습니다.");
  				location.reload();	
  				
  			}if(result=="reject"){
  				alert("작성자만 수정할 수 있습니다.")
  			}
  			
  	      },
  	      error: function(xhr, status, error) {
  	      	alert("통신실패");
  	      }  
  	   })
	}
	// 댓글 삭제
	function comm_del(obj, commId){
	
		var commId = commId;
	
		let delData={
				"comm_id" : commId	
		};
		
		if(confirm("삭제하시겠습니까?")){
			$.ajax({
	  	      type  : "POST",
	  	      url    : '/board/commDelDate.do',
	  	      data: delData,
	  	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
	  	      success: function(result){
	  	      	
	  			if(result=="confirm"){
	  				alert("삭제가 완료되었습니다.");
	  				location.reload();	
	  			}if(result=="reject"){
	  				alert("작성자만 삭제할 수 있습니다.")
	  			}
	  	      },
	  	      error: function(xhr, status, error) {
	  	      	alert("통신실패");
	  	      }  
	  	   })
		}
	}
	/*게시글 삭제*/
	function del_board(obj, boardId){
		
		var commId = commId;
	
		let delData={
				"board_id" : boardId	
		};
		
		if(confirm("삭제하시겠습니까?")){
			$.ajax({
	  	      type  : "POST",
	  	      url    : '/board/delOneBoardList.do',
	  	      data: delData,
	  	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
	  	      success: function(result){
	  	    	opener.document.location.reload();
  				self.close();	
	  	      },
	  	      error: function(xhr, status, error) {
	  	      	alert("통신실패");
	  	      }  
	  	   })
		}
	}
	
	// 답글 등록페이지 이동
	function openAnswerForm(target) {
		  location.href = '/board/boardRegister.do?board_id=' + target +'&type=ans';     				    		      
    } 
	
	$(document).ready(function() {
		///board/boardList.do
			
		
		//댓글 작성 창 토글
		$('.write_comm').click(function(e){	
			target = e.target;
			$(target).siblings('.writeBox').toggle();
		})
		//게시글 저장
		$('.comm_save').click(function(e){
			var target = e.target.parentNode;	
			var content = target.querySelector('textarea').value;	
			const urlParams = new URL(location.href).searchParams;
			const boardId = urlParams.get('board_id');
			
			if(content == ""){
				alert("내용을 입력해주세요")
				return false;
			}
			if($(content).length >= 2000){
				alert("2000자 이상 등록 할 수 없습니다.")
				return false;
			}
			
			 var formData={
						"comm_content" : content,
						"comm_parent" : 0,
						"board_id" : boardId
					};
		 	$.ajax({
      	      type  : "POST",
      	      url    : '/board/commdPost.do',
      	      data: formData,
      	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
      	      success: function(result){
      	    	console.log(result);
      			location.reload();	
      	      },
      	      error: function(xhr, status, error) {
      	      	alert("통신실패");
      	      }  
      	   }); 
		})
	});
</script>
</html>