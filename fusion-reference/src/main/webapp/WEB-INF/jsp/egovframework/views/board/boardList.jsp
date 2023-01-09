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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/board/boardList.css'/>" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<title>퓨전 게시판(목록)</title>
</head>
<body>
	<main class="mt-5 pt-5">
		<div class="container-fluid px-4" id="list-container">
			<div id="nav_container">
	            <nav id="nav_menu">
	                <ul id="nav_list">
	                    <li>
	                        <a href="#">게시판</a>
	                    </li>
	                    <li>
	                        <a href="/imgBoard/imgBoardList.do?viewPage=1&&search=&&searchText=&&pageFilter=10">갤러리 게시판</a>
	                    </li>
	                </ul>
	            </nav>
	         </div>
			<h1 class="mt-4">게시판</h1>
			<div class="upper">
				<div class="upper_left">
					<form name="searchContainer" method="get"
						action="/board/boardList.do">
						<input type="hidden">
						<input type="hidden" name="viewPage" value="1">
						<select name="search" id="search">
							<option ${(param.search == "all")?"selected":""} value="all">전체</option>
							<option ${(param.search == "title")?"selected":""} value="title">제목</option>
							<option ${(param.search == "writer")?"selected":""} value="writer">작성자</option>
							<option ${(param.search == "content")?"selected":""} value="content">내용</option>
						</select> 
						<input type="text" name="searchText" id="searchText" value="${param.searchText }" >
						<select name="pageFilter" id="pageFilter">
							<option ${(param.pageFilter == "10")?"selected":""} value="10" selected>10개씩 보기</option>
							<option ${(param.pageFilter == "20")?"selected":""} value="20">20개씩 보기</option>
							<option ${(param.pageFilter == "30")?"selected":""} value="30">30개씩 보기</option>
						</select>
						<button class="btn btn-light" type="submit" onclick="search()">검색</button>
						
					</form>
				</div>
				<div class="upper_right">
					<%
					String id = (String) session.getAttribute("id");
					%>
					<%
					if (id != null) {
					%>
					<div class="welcome">
						<%=id%>
						님
					</div>
					<button type="button" class="btn btn-danger"
						onclick="location.href='/user/logout.do'">로그아웃</button>
					<%
					} else {
					%>
					<button class="btn btn-success"
						onclick="location.href='/user/loginForm.do?'">로그인</button>
					<button id="btn_reg" class="btn btn-success" 
						onclick="location.href='/user/registerPage.do'">회원가입</button>
					
					<%
					}
					%>
				</div>
			</div>
			<div class="card mb-4">
				<div class="card-header">
					<div class="total">총 ${total}개</div>
					<c:if test="${id ne  null}"> 
					<div class="btn_container">
						<a id="del_btn" class="btn btn-danger float-end"> <i
							class="fas fa-edit"></i> 삭제
						</a>
						<a id="move_write" class="btn btn-primary float-end"
							onclick="openIns()"> <i class="fas fa-edit"></i> 글 작성
						</a>
					</div>
					</c:if>
				</div>
				<div id="list_container" class="card-body">
					<table id="board_list" class="table table-hover table-striped">
						<thead>
							<tr>
								<th width="50px"><input type="checkbox" id="All_check"></th>
								<th width="100px">글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList}" var="board">
								<tr>
									<td>
									<c:if test="${board.writer == id }">
									<input type="checkbox" name="chk" class="chk" value="${board.board_id}">
									</c:if>
									
									</td>
									<td>${board.board_num}</td>
									<td class="move_content" onclick="openDetail(${board.board_id})">
										<c:forEach var="i" begin="1" end="${board.level -1}">
												&nbsp;&nbsp;
										</c:forEach>
										<c:if test="${board.rep_parent > 0}">
											<img src="/image/arrow.png">
										</c:if>											
										${board.title}
									</td>
									<td class="boardId">${board.id}</td>
									<td>${board.board_cnt}</td>
									<td>${board.regist_dt}</td>										
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<nav aria-label="...">
				<ul class="pagination pagination-lg" >	
					<c:if test="${(param.viewPage != 1)}">
						<li class="page-item">
							<a class="page-link"
			   	   			   href="../board/boardList.do?viewPage=${(param.viewPage-1) }&&search=${(param.search)}&&searchText=${(param.searchText)}&&pageFilter=${(param.pageFilter)}">Previous</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="1" end="${totalPage }">
						<li class="page-item"><a class="page-link"
							href="../board/boardList.do?viewPage=${i }&&search=${(param.search)}&&searchText=${(param.searchText)}&&pageFilter=${(param.pageFilter)}">${i }</a>
						</li>
					</c:forEach>
					<c:if test="${(param.viewPage != totalPage) }">
						<li class="page-item"><a class="page-link" 
							href="../board/boardList.do?viewPage=${(param.viewPage+1) }&&search=${(param.search)}&&searchText=${(param.searchText)}&&pageFilter=${(param.pageFilter)}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
			
				<div class="card mb-4" id="nt_container">
					<div class="card-body" id="n">
						<h1 class="mt-4">공지사항</h1>
						<c:forEach items="${notice}" var="notice">
							<div class="mb-3">
								<label for="title" class="form-label"></label> 
								<input
									type="text" class="form-control" id="title" name="title" readonly
									value="${notice.title }">
 							</div> 
							<div class="mb-3">
								<label for="content" class="form-label"></label>
								<textarea class="form-control" id="content" name="content" value="" readonly>${notice.content }</textarea>
							</div>
						</c:forEach>
						<div class="popUp_bottom">
							<div class="popup_chk_container">
	                            <input type="checkbox" name="popup_chkbox" id="popup_chkbox"><div class="popup_chk_text">오늘 하루 보지 않기</div>
							</div>	
								<button class="btn btn-outline-primary" id="close_nt">닫기</button>
						</div>
					</div>
				</div>
			
		</div>
	</main>

</body>
<script src="https://code.jquery.com/jquery-3.6.1.js"
	integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	
	/*쿠키 설정하기*/
	let setCookie = function(cname, cvalue, exday){
        let today = new Date();
        today.setTime(today.getTime() + (exday*24*60*60*1000));
       
        let expireDay = "expireDay=" + today;
        document.cookie = cname + "=" + cvalue + ";" + expireDay; 
	 }
	
	/*쿠키 가져오기*/
	let getCookie = function(cname){
	    let name = cname + "=";
	    let separator = document.cookie.split(';');
	    
	    for(let i=0; i<separator.length; i++){
	        let cInfo = separator[i];
	        while(cInfo.charAt(0) == '') cInfo = cInfo.substring(1);
	        if (cInfo.indexOf(name) != -1)
	        return cInfo.substring(name.length, cInfo.length);
	   	}
	    return "";
	 }
		
	/* 팝업창 닫기*/
	let closePopup = function(){
		
		if($("input[name=popup_chkbox").is(":checked") == true){
	   	setCookie("close","Y",1);
		}
		$('#nt_container').hide();
	}
	
	function openDetail(target){
		window.open(
	       	'/board/boardPost.do?board_id=' + target,
	       	'show_detail',
	       	'resizable=yes'						    		       	
		);
	}
	/*게시글 등록창 열기*/
	function openIns(){
		window.open(
	       	'/board/boardRegister.do',
	       	'opneIns',
	       	'resizable=yes'						    		       	
		)
	};  		
	
	$(document).ready(function() {
		/*쿠키가 있는지 확인*/
		let cookieData = document.cookie;
	 		
		if(cookieData.indexOf("close=Y")<0){
	   		$('#nt_container').show();
			}else{
	   		$('#nt_container').hide();
			}
	 	
		$('#close_nt').click(function(){
	    	closePopup();
			})
	
		/*게시글 선택*/
		$("#All_check").click(function() {
	        if($("#All_check").is(":checked")) {
	        	$(".chk").prop("checked", true);
	        }
	        else {
	        	$(".chk").prop("checked", false);
	        }
	    });
	    
	    $(".chk").click(function() {
	        var total = $(".chk").length;
	        var checked = $(".chk:checked").length;
	        
	        if(total != checked) $("#All_check").prop("checked", false);
	        else $("#All_check").prop("checked", true); 
	    });
	    
	    $('#close_nt').click(function(){
	    	$('#nt_container').css("display","none");
	    })
	    <!-- 게시글 여러건 삭제 -->
	    $('#del_btn').click(function(e){
	    	//chkInfo에는 체크된 체크박스태그만 들어감
	    	let chkInfo = $("input:checkbox[name='chk']:checked");
	    	let delBoardId = []
	    	if($("input:checkbox[name='chk']:checked").length === 0){
	    		alert("삭제할 항목을 선택해 주세요")
	    		return;
	    	}else {
	    		for(let i=0; i<chkInfo.length; i++){              		
	    			let chkTag = $(chkInfo[i]);
	        		
	        		delBoardId.push($(chkTag).val());  
	        	}
	    	}
	    	if(!confirm("삭제하시겠습니까?")){
	    		return fasle;
	   		}
	    	 $.ajax({
	    	      type  : "GET",
	    	      url    : '/board/delBoardList.do',
	    	      data: {
	    	    	  delBoardId : delBoardId
	    	      },
	    	      success: function(result){
	    	    	console.log(result);
	    			location.reload();	
	    	      },
	    	      error: function(xhr, status, error) {
	    	      	alert(error);
	    	      }  
	    	   });
		   	 })     
		});
</script>
</html>