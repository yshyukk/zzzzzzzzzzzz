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
<title>퓨전 게시판(작성)</title>
</head>
<body>

	<main class="mt-5 pt-5">
	<div class="container-fluid px-4">
	
		<c:set var="type" value="${param.type }"/>
		<c:choose>
			<c:when test="${type eq 'ans' }">
				<h1 class="mt-4"><span class="ans_text">답글 작성</span></h1>
			</c:when>
			<c:otherwise>
				<h1 class="mt-4">게시글 작성</h1>
			</c:otherwise>
		</c:choose>
		<div class="card mb-4">
			<div class="card-body">
				<form id="ins_frm" >
					
					<select name="select_board" id="select_board">
						<c:if test="${type ne 'ans'}">
							<option value="posting" >게시글</option>
							<option value="notice" >공지사항</option>
						</c:if>
						<c:if test="${type eq 'ans'}">
							<option value="answer" ${(param.ins_frm == "answer")?"selected":""}>답글</option>
						</c:if>
					</select>
					
					<div class="mb-3">
						<label for="title" class="form-label">제목</label>						
						<c:choose>
							<c:when test="${type eq 'ans' }">
								<input type="text" class="form-control" id="title" name="title" value="[ 답글 ]  ">
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control" id="title" name="title" value="">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="mb-3">
						<label for="content" class="form-label">내용</label>
						<textarea class="form-control" id="content" name="content" value="" placeholder="최대 2000까지 작성가능"></textarea>
					</div>
					<button class="btn btn-outline-warning" id="board_ins" >등록</button>
					<button class="btn btn-outline-warning" id="close_ins" >닫기</button>
				</form>
			</div>
		</div>
	</div>
	</main>
</body>
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript">
	
$('#board_ins').click(function(){
	
	const urlParams = new URL(location.href).searchParams;
	const boardId = urlParams.get('board_id');
	
	let title = $('#title').val();
	let content = $('#content').val();
	let select_board = $('#select_board').val();
	let formData = {};
	
	if(title == ""){
		alert("제목을 입력해주세요")
		$('#title').focus();
		return false;
	}
	if(title.length > 100){
		alert("최대 100자까지 입력할 수 있습니다.")
	}
	if(content == ""){
		alert("내용을 입력해주세요")
		$('#content').focus();
		return false;
	}
	if(content.length > 2000){
		alert("최대 2000자까지 입력할 수 있습니다.")
	}
	
	if(boardId != null){
		formData = {
			"title" : title,
			"content" : content,
			"select_board" : select_board,
			"rep_parent" : boardId
		}	
	}else{
		formData = {
			"title" : title,
			"content" : content,
			"select_board" : select_board
		}
	}
	$.ajax({
	      type  : "POST",
	      url    : '/board/insBoardPost.do',
	      data: formData,
	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
	      success: function(result){
	    	opener.document.location.reload();
			self.close();
	      },
	      error: function(xhr, status, error) {
	      	alert(error);
	      }  
	   }); 
	});
	
	$(document).ready(function() {
		$('#close_ins').click(function(){
			opener.document.location.reload();
			self.close();
		}) 
	})
</script>
</html>