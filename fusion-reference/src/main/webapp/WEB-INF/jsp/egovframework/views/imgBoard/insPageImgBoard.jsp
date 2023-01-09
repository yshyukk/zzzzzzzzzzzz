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
		<h1 class="mt-4">게시글 등록</h1>
		<div class="card mb-4">
			<div id="ins_container" class="card-body">
				<div class="card-body">
				<form name="form_contents " id="form_contents" method="post" enctype="multipart/form-data">
					<div class="mb-3">
						<label for="title" class="form-label">제목</label>
						<input type="text" class="form-control" id="title" name="title" placeholder="최대 30자까지 입력가능합니다.">
					</div>
					<div class="mb-3">
						<label for="content" class="form-label">내용</label>
						<textarea class="form-control" id="content" name="imgBoardContent" placeholder="최대 2000자까지 입력가능합니다."></textarea>
					</div>
					<div id="hashTag_container" class="mb-3">
						<label for="hashtag" class="form-label">해시태그</label>
						<input type="text" class="hashTag hashTag form-control"  name="hashTag" id="hashTag" >
						<button type="button" id="ins_hashTag">입력</button>
					</div>
					<div id="show_hashtag" class="hashTag form-control" style="display:inline-block; border:1px solid lightgray;">
						<ul id="hashTag_list">
							<!-- 태그 보여줄 부분 -->
						</ul>
					</div>
					<div class="mb-3">
						<label for="writer" class="form-label">작성자</label>
						<input type="text" class="form-control" id="writer" name="imgBoardWriter" value="${id}" disabled>
					</div>
                	<div id="upload_container" class="form-label" >
                    사진등록
                    
                        <ul id="img_add_container">
                            <li style="list-style: none;" class="ins_img">
                                <input type="file" name="imgae" style="margin-top: 10px; border: 1px solid lightgray; padding: 5px; width: 500px;">
                                <button type="button" class="remove_fileInfo btn btn-outline-danger">취소</button>
                                <button type="button" class="add_file btn btn-outline-success">+</button>
                            </li>
                        </ul>
                        <div id="btn_img">
                            <button type="button"  class="btn btn-outline-primary" id="btn_golist">목록</button>
                            <button type="button" class="btn btn-outline-success" id="btn_imgSubmit">저장</button>
                        </div>
                      </div>
                    </form>
                </div>
            </div>
		</div>
	</div>

	</main>
	
</body>
<script type="text/javascript">
	let tag = {};
	var counter = 0;
	
	function addHashTag(value){
		tag[counter] = value; //태그를 object안에 추가
		counter ++; //counter 증가, 삭제를 위한 del_btn의 고유 id
	}
	
	$(document).ready(function(){
		//최종적으로 서버에 넘길때 tag안에 있는 값을 배열로 만들어서 넘긴다
		function saveTag(){
			return Object.values(tag).filter(function(word){
				return word !== "";
			});	
		}
		
		$("#ins_hashTag").on("click", function(e){
			let self = $('#hashTag');
			let hashTagVal = self.val();
			
			//해시태그 입력 글자 수 제한
			if(hashTagVal.length > 5){
				alert('최대 5글자까지 입력할 수 있습니다.')
				return false;
			}
			//해시태그 등록 갯수 제한
			if($('.span_hashTag').length > 4){
				alert('최대 5개까지 입력할 수 있습니다.')
				return false;
			}
			// 중복값 체크 및 등록
			if(hashTagVal !== ""){
				let result = Object.values(tag).filter(function (word){
					return word === hashTagVal;
					console.log ("hashTagVal111::" + result);
				})
				if(result.length == 0){
					$("#hashTag_list").append("<li class='hashTag_item'>" + "<span class='span_hashTag'>" +hashTagVal +"</span>" + 
											  "<button type='button' class='del_btn' idx='" + counter + "'>x</button></li>");
					addHashTag(hashTagVal);
					self.val('');
					console.log($('span_hashTag').length)
					
				}else {
					alert("태그는 중복해서 입력할 수 없습니다.")
				}	
			}
			
		});
		
		//삭제버튼
		$(document).on("click", ".del_btn", function(e){
			let index = $(this).attr("idx");
			console.log("index=" + index )
			tag[index] = "";
			$(this).parent().remove();
			
		});
	
	})
	//첨부파일 등록창 제한
	$('.remove_fileInfo').click(function(){
	    if($('.ins_img').length > 1){
	        $(this).parent().remove();
	    }    
	})
	//이미지 등록 창 생성
	$('.add_file').click(function(){
		if($('.ins_img').length < 5){
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
	
	$('#btn_imgSubmit').click(function(){
		let title = $('#title').val();
		let content = $('#content').val();
		let firstLi = $('#img_add_container').find('li').eq(0);
		let img = firstLi.find('input[type="file"]').val();
		
		if(title == ""){
			alert("제목을 입력해주세요.");
			return false;
		}
		if(title.length > 30){
			alert("최대 30자까지 입력가능합니다.")
			return false;
		}
		if(content == ""){
			alert("내용을 입력해주세요");
			return false;
		}
		if(content.length > 2000){
			alert("최대")
			return false;
		}
		if(img == ""){
			alert("최소 하나의 이미지는 등록해주시기 바랍니다.")
			return false;
		}
		
		let imgName =[];
		let imgVal = $('input[type="file"]').val();
		imgName.push(imgVal)
		
		let data = $('#form_contents')[0];
		let formData = new FormData(data);
	
		let hashTagList = Object.values(tag);
	
		
		formData.append("hashTagList", hashTagList)
		  $.ajax({
		      type  : "POST",
		      enctype : 'multipart/form-data',
		      url    : '/imgBoard/insImgBoard.do',
		      data:  formData,	 
		      processData: false,	
			  contentType: false,
		      success: function(result){
		    	
	  	      opener.document.location.reload();
			  self.close();	   
	  	    		
	  	    },
		      error: function(xhr, status, error) {
		      	  alert("통신실패");
		      	}  
		   })  
	})
	
	$('#btn_golist').click(function(){
		opener.document.location.reload();
		self.close();
	})
  
	
</script>
</html>