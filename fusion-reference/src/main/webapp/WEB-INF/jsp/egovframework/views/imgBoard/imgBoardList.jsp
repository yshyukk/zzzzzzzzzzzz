<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 <link rel="stylesheet" type="text/css" href="<c:url value='/css/imgBoard/imgBoardList.css'/>" />

</head>
<body>
    <div id="gallery_wrap">
        <div id="nav_container">
            <nav id="nav_menu">
                <ul id="nav_list">
                    <li>
                        <a href="/board/boardList.do">게시판</a>
                    </li>
                    <li>
                        <a href="#">갤러리 게시판</a>
                    </li>
                </ul>
            </nav>
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
        <div id="main_container">
        <div class="fn_main">
	       <form name="searchContainer" method="get" action="/imgBoard/imgBoardList.do">
				<input type="hidden">
				<input type="hidden" name="viewPage" value="1">
				<select name="search" id="search">
					<option ${(param.search == "all")?"selected":""} value="all">전체</option>
					<option ${(param.search == "title")?"selected":""} value="title">제목</option>
					<option ${(param.search == "writer")?"selected":""} value="writer">작성자</option>
					<option ${(param.search == "content")?"selected":""} value="content">내용</option>
					<option ${(param.search == "tag")?"selected":""} value="tag">태그</option>
				</select> 
				<input type="text" name="searchText" id="searchText" value="${param.searchText }" >
				<select name="pageFilter" id="pageFilter">
					<option ${(param.pageFilter == "10")?"selected":""} value="10" selected>10개씩 보기</option>
					<option ${(param.pageFilter == "20")?"selected":""} value="20">20개씩 보기</option>
					<option ${(param.pageFilter == "30")?"selected":""} value="30">30개씩 보기</option>
				</select>
				<button class="btn btn-light" type="submit" onclick="search()">검색</button>
			</form>
        	<div class="fn_main_btn">
        		<button class="btn_write btn btn-success" style="width:50px; margin-right:100px;">작성</button>
       	 	</div>
        </div>
        <div id="contents_list">
        </div>

        </div>
    </div>
</body>
<script>
	const urlParams = new URL(location.href).searchParams;
	let viewPage = urlParams.get('viewPage');
	let search = urlParams.get('search');
	let searchText = urlParams.get('searchText');
	let pageFilter = urlParams.get('pageFilter');
	$(document).ready(function() {
        getList()
         
    });
	
	function getList(){
		   $.ajax({
               url : '/imgBoard/listElement.do',
               data: {
	            	  "viewPage":viewPage,
	            	  "search":search,
	            	  "searchText":searchText,
	            	  "pageFilter":pageFilter, 
               		 },
         	   dataType : "html",
               success : function(result) {
            	  $("#contents_list").empty(); 
                  $("#contents_list").append(result);     
           }
       })
	}
	
	
	$(document).on("click", "button[name='like_btn']", function(){
		let likeYn = $(this).prev().prev().val();
		let imgBoardId = $(this).prev().val();
		/*target = $(this).parent()
		console.log(target);
		
		if(likeYn === undefined){
			$(this).parent().hide()
			alert("remove")
		} */
		
		console.log(likeYn);
		console.log(imgBoardId);
		
	   	$.ajax({
		      type  : "POST",
		      url   : '/imgBoard/like.do',
		      data: {
		    	  	"imgBoardId" : imgBoardId,
		    	  	"likeYn" : likeYn
		    	  	},
		      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		      success: function(result){
		    	  console.log(result)
		    	  if(result == "success"){
		    		  getList();
		    	  }
		      },
		      error: function(xhr, status, error) {
		      	alert(error);
		      }  
	     })
	}) 
	
	
	/* $('.btn_like').click(function(){
	let likeYn = $(this).prev().prev().val();
	let imgBoardId = $(this).prev().val();
	
	console.log(likeYn);
	console.log(imgBoardId);
	
   	$.ajax({
	      type  : "POST",
	      url   : '/imgBoard/like.do',
	      data: {
	    	  	"imgBoardId" : imgBoardId,
	    	  	"likeYn" : likeYn
	    	  	},
	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
	      success: function(result){
	    	  console.log(result)
	    	  if(result == "success"){
	    		  console.log(result)
	    		  opener.parent.getList();
	    	  }
	      },
	      error: function(xhr, status, error) {
	      	alert(error);
	      }  
	   })
	}) 
  */
		
	$('.btn_listReset').click(function(){
		location.href='/imgBoard/imgBoardList.do?viewPage=1&&search=&&searchText=&&pageFilter=10';
	})
	
	$('.btn_write').click(function(){
		window.open(
	       	'/imgBoard/insPageImgBoard.do',
	       	'opneIns',
	       	'resizable=yes'						    		       	
		)
	})
</script>


</html>