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
   <c:if test="${fn:length(imgBoardList) != 0 }">	
         <c:forEach items="${imgBoardList }" var="list" >
            <div class="content_container">
                <div class="content_img">
                	<!-- ${list} -->
             		<a href="/imgBoard/detailPage.do?imgBoardId=${list.imgBoardId}">
                  		<img src="${list.thumbNailName}">
             		</a>			
                </div>
            		<div id="like_container2" style="border-bottom: 1px solid lightgray">
              			${list.liked }  
			            <input type="hidden" value="${list.liked }" name="ynVal">
		                <input type="hidden" value="${list.imgBoardId }">
		                <button  name="like_btn" type="button" style="width:50px; height:50px; border:none; background-color:transparent">
              				<c:choose>
	                		<c:when test="${list.liked  == 'Y'}">
	                		   <img src="/image/like.png" style="width:35px; height:35px;background-color:transparent;">
	                		   <span>${list.likeCnt }</span>
	                  		</c:when>
	                  		<c:otherwise>
	                  			<img src="/image/heart.png" style="width:30px; height:30px;background-color:transparent;">	
	                  		</c:otherwise>
	                	</c:choose>
		                </button>
	            	</div>
 	
                <div class="title">
                    ${list.title }
                </div>  
               <c:forEach items="${hashTagList}" var="hashTagList">
              		<c:if test="${list.imgBoardId == hashTagList.imgBoardId}">
                		 <a href="/imgBoard/imgBoardList.do?viewPage=1&search=tag&searchText=${hashTagList.tag }&pageFilter=10">#${hashTagList.tag }</a>
                	</c:if>
               </c:forEach>
            </div>
      	 </c:forEach>
      	
         <div id="paging_container">
         	현재 ${param.viewPage }페이지입니다.
             
  			<nav aria-label="...">
				<ul class="pagination pagination-lg" >	
					<c:if test="${(param.viewPage != 1)}">
						<li class="page-item">
							<a class="page-link"
			   	   			   href="../imgBoard/imgBoardList.do?viewPage=${(param.viewPage-1) }&search=${(param.search)}&searchText=${(param.searchText)}&pageFilter=${(param.pageFilter)}">Previous</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="1" end="${totalPage }">
						<li class="page-item"><a class="page-link"
							href="/imgBoard/imgBoardList.do?viewPage=${i }&search=${(param.search)}&searchText=${(param.searchText)}&pageFilter=${(param.pageFilter)}">${i }</a>
						</li>
					</c:forEach>
					<c:if test="${(param.viewPage != totalPage) }">
						<li class="page-item"><a class="page-link" 
							href="/imgBoard/imgBoardList.do?viewPage=${(param.viewPage+1) }&search=${(param.search)}&searchText=${(param.searchText)}&pageFilter=${(param.pageFilter)}">Next</a>
						</li>
					</c:if>
				</ul>
			</nav>
         </div>   
     </c:if>
   	<c:if test="${fn:length(imgBoardList) == 0}">
			검색결과가 없습니다
   	</c:if>         	
</body>
<script>
	
	$(document).ready(function() {
		
	});
	
	
	$('.btn_write').click(function(){
		window.open(
	       	'/imgBoard/insPageImgBoard.do',
	       	'opneIns',
	       	'resizable=yes'						    		       	
		)
	})
</script>
</html>