<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/imgBoard/insPageImgBoard.css'/>" />
</head>
<body>
	<form name="upform_contents " id="upform_contents" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="title" class="form-label">제목</label>
			<input type="text" class="form-control" id="title" name="title" value="${boardInfo.title }">
		</div>
		<div class="mb-3">
			<c:forEach items="${imgInfo }" var="imgInfo">
				<img src="${imgInfo.imgSavePath}/${imgInfo.imgSaveName}">
			</c:forEach>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">내용</label>
			<textarea class="form-control" id="content" name="imgBoardContent" >${boardInfo.imgBoardContent }</textarea>
		</div>
		<div class="mb-3">
		<div id="hashTag_container" class="mb-3">
			<label for="hashtag" class="form-label">해시태그</label>
			<input type="text" class="hashTag hashTag form-control"  name="hashTag" id="hashTag" >
			<button type="button" id="ins_hashTag">입력</button>
		</div>
		
			 <div id="show_hashtag" class="hashTag form-control" style="display:inline-block; border:1px solid lightgray;">
				<ul id="hashTag_list">
					<!-- 태그 보여줄 부분 -->
					<c:forEach items="${hashTag}" var="hashTag">
						<li class='hashTag_item'>
							<span class='span_hashTag'>${hashTag.tag }</span>
							<button type='button' class='del_btn'>x</button>
						</li>
					</c:forEach>
				</ul>
			</div>
	
		<%-- <c:forEach items="${hashTag}" var="hashTag">
			<label for="hashtag" class="form-label">해시태그</label>
			<input type="text" class="hashTag form-control"  name="hashTag" value="${ hashTag.tag}" >
			<input type="hidden" name="listHashTagId" value="${hashTag.hashTagId }">
		</c:forEach> --%>
		
		</div>
		<div class="mb-3">
			<label for="imgBoardRegistDt" class="form-label">등록일</label>
			<input type="text" class="form-control" id="imgBoardRegistDt" name="imgBoardRegistDt" value="${boardInfo.imgBoardRegistDt}" >
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">작성자</label>
			<input type="text" class="form-control" id="writer" name="imgBoardWriter" value="${boardInfo.imgBoardWriter }" >
		</div>
       	<div id="upload_container" class="form-label" >
            <div class="mb-3" >다운로드
            	<ul style="list-style:none" id="img_container">
             	<c:forEach items="${imgInfo}" var="imgInfo">
             	${imgInfo.imgId }
             		<li class='ins_img' style="font-size:20px; margin-bottom:10px;">
						<img src="${imgInfo.imgSavePath}/${imgInfo.imgSaveName}">
						<a href="${imgInfo.imgSavePath}/${imgInfo.imgSaveName}" download>${imgInfo.imgSaveName}</a>		                    			
						<input type="hidden" value="${imgInfo.imgId }" name="origin_imgId">
						
             		    <button type="button" class="btn btn-outline-danger" id="btn_del" style="margin-left:10px">삭제</button>
             		    <input type="file" class="btn-outline-danger"  style="margin-top: 10px; border: 1px solid lightgray; padding: 5px; width: 500px; display: none;">
             			<%--이미지 프리뷰 --%>
             			<input class="preview_input" type="file" name="updateImg">
             			<%-- <img class="preview" style="display:none">--%>
             		</li>
             	</c:forEach>	
            	</ul>
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
            </div>
            <div id="btn_img">
                <button type="button"  class="btn btn-outline-primary" id="btn_golist" onclick="go_list();">목록</button>
                <button type="button" class="btn btn-outline-success" id="btn_updateSave" >수정</button>
                
            </div>
        </div>
   </form>
</body>
<script>
let updImgsInfo = [];
let originImgIdArr= new Array();

//해시태그
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
})
	//삭제버튼
	$(document).on("click", ".del_btn", function(e){
		let index = $(this).attr("idx");
		console.log("index=" + index )
		tag[index] = "";
		$(this).parent().remove();
		
	});

	//목록으로
	function go_list(){
		location.href = 'http://localhost:8080/imgBoard/imgBoardList.do?viewPage=1&search=&searchText=&pageFilter=10';
	}

	//변경 눌렀을 때 이미지 등록 input 실행
	$('.btn_imgUpd').click(function(){
		let findOriginInfo=$(this).next().next().val(); //바꾸기전 등록된 파일 정보		
		$(this).next().click();
	})
	
	//미리보기 및 업데이트 이미지 정보
	$('.preview_input').on("change", function(e){
		let showPre = $(this).next();
		let imgId = $(this).parent().find('input[name=origin_imgId]').val();
		originImgIdArr.push(imgId);
		
		let file = e.target.files[0];
		console.log("updateImgInfo : " , file)
		updImgsInfo.push(file)
		console.log("arr : " , updImgsInfo)
		
		let ext = file.name.split(".").pop().toLowerCase();
		let maxSize = 3* 1024* 1024; //3MB
		//업로드 파일 제한
		if($.inArray(ext,["jpg","jpeg","png","jfif","gif"]) === -1){
			alert('이미지만 등록할 수 있습니다.\n (등록가능한 확장자: jpg, jpeg, png, jfif, gif)');

		    $(showPre).css("display", "none");
			$(this).val("");
			return false;
		}
		
		let reader = new FileReader();

		reader.onload = function(e){

			$(showPre).attr("src", e.target.result);
			$(showPre).css("display", "block");
		}

		reader.readAsDataURL(file)
		
	})
	
	//이미지 삭제
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
	//수정완료 버튼
	$('#btn_updateSave').click(function(){
	
	let title = $('#title').val()
	let content = $('#content').val()
	let hashTag = $('.hashTag').val()
	let firstLi = $('#img_container').find('li').eq(0)
	let img = firstLi.find('img').val()
	
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
	
	let hashTagList = [];
	let selectHashTag = $('.span_hashTag')
	let hashTagLen = selectHashTag.length;
	
	for(let i=0; i<hashTagLen; i++){
		hashTagList.push(selectHashTag.eq(i).text())
	}
	
	let formData = new FormData(data);
	formData.append("imgBoardId", imgBoardId);
	formData.append("originImgId", originImgIdArr);
	formData.append("hashTagList", hashTagList);

	
	console.log(hashTagList);
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
	
	
</script>
</html>