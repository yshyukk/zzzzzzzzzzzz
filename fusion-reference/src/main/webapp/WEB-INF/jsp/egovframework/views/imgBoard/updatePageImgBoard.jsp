<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 <link rel="stylesheet" type="text/css" href="<c:url value='/css/imgBoard/insPageImgBoard.css'/>" />
</head>
<body>
	<main class="mt-5 pt-5">
		<div class="container-fluid px-4">
			<h1 class="mt-4">게시글 수정</h1>
			
			<div class="card mb-4">
				<div class="card-body" id="upd_container">
				
					
	            </div>
			</div>
		</div>
	
	</main>
	
</body>
<script type="text/javascript">

const urlParams = new URL(location.href).searchParams;
const imgBoardId = urlParams.get('imgBoardId');


 	$(document).ready(function() {
	    getList()
	     
	});
	function getList(){
		   $.ajax({
	           url : '/imgBoard/updElement.do?imgBoardId=' + imgBoardId,
	     	   dataType : "html",
	           success : function(result) {
	        	  $("#upd_container").empty(); 
	              $("#upd_container").append(result);     
	       }
	   })
	}

$('#btn_del').click(function(){
	let imgId = $(this).prev().val();
	
	
	let del_conf = confirm('삭제하시겠습니까?')
	let data = {
		"imgId" : imgId,
		"imgBoardId" : imgBoardId
	}
	if(del_conf == false){
		return false
	}
		$.ajax({
	      type  : "POST",
	      url    : '/imgBoard/delImg.do',
	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
	      data: data,
	      
	      success: function(result){
	      
	      alert("삭제가 완료되었습니다.")
		  location.href = '/imgBoard/imgBoardList.do';
	    	 	
	      },
	      error: function(xhr, status, error) {
	      	alert("통신실패");
	      }  
	 	})  
})
$('.remove_fileInfo').click(function(){
    if($('li').length > 1){
        $(this).parent().remove();
    }    
})
$('.add_file').click(function(){
	if($('li').length < 5){
		let ul = $('#img_add_container');
        let li = $('#img_add_container').find('li').eq(0).clone(true);
        let input = li.find('input[type="file"]').val("").clone(true);
         
        li.find('input[type="file"]').remove('input[type="file"]');
        li.prepend(input);
        ul.append(li);
	}else{
		alert("최대 5개까지 등록할 수 있습니다.")
	}  
   return false;
})
$('#btn_updateSave').click(function(){
	
	alert('sdasd');
	let title = $('#title').val()
	let content = $('#content').val()
	let hashTag = $('.hashTag').val()
	let firstLi = $('#img_container').find('li').eq(0)
	let img = firstLi.find('img').val()
	
	console.log(hashTag)
	console.log(hashTag.length)
	if(title == ""){
		alert("제목을 입력해주세요.");
		return false;
	}
	if(title.length > 20){
		alert("최대 20자까지 작성가능합니다.")
		return false;
	}
	if(content == ""){
		alert("내용을 입력해주세요");
		return false;
	}
	if(content.length > 2000){
		alert("최대 2000자 까지 작성 가능합니다.")
	}
	
	const urlParams = new URL(location.href).searchParams;
	const imgBoardId = urlParams.get('imgBoardId');
	
	let data = $('#upform_contents')[0];
	console.log("data:::",data)
	
	let formData = new FormData(data);
	formData.append("imgBoardId", imgBoardId);
	
	$.ajax({
	      type  : "POST",
	      enctype : 'multipart/form-data',
	      url    : '/imgBoard/update.do',
	      data: formData,
	      processData: false,	
		  contentType: false,
	      success: function(result){
	    	
	    	  location.href = '/imgBoard/updatePageImgBoard.do?imgBoardId=' + imgBoardId;
	    		
	      },
	      error: function(xhr, status, error) {
	      	alert("통신실패");
	      }  
   	 })  
})

$(document).ready(function() {
})
	
</script>
</html>