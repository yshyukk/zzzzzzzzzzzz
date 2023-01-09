<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script 
  src="http://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous">
 </script>
</head>
<body>
	 <form name="register" id="reg_form">
	  <label>아이디</label><input type=text name="id" id="id" placeholder="영어나 숫자, 5 ~ 20자"><button type="button" id="idCheck">중복검사</button><br/>
		<label>이름</label><input type=text name="ncnm" id="ncnm"><br/>
		<label>비밀번호</label><input type="password" name="pwd" id="pwd" placeholder="영문,특수문자,숫자 포함 8자 이상" width="100px"><br/>
		<label>비밀번호 확인</label><input type="password" name="pwdChk"id="pwdChk" width="100px"><br/>
		<button type="button" id="reg_btn">가입</button>
		<button type="button" onclick="moveLoginForm()">로그인</button> 	 
	 </form>	
</body>



<script>
	/*submit버튼*/
	$('#reg_btn').click(function(){
		/*회원정보 NULL값 검사*/
		let idVal =  $('#id').val(); 
		let ncnmVal = $('#ncnm').val();
		let pwdVal = $('#pwd').val();
		let pwdChkVal = $('#pwdChk').val();
		
		if(idVal == ""){
			alert("아이디를 입력해 주세요")
			$('#id').focus();
			return false;
		}
		
		if(ncnmVal == ""){
			alert("이름을 입력해 주세요")
			$('#ncnm').focus();
			return false;
		}
		
		if(pwdVal == ""){
			alert("비밀번호를 입력해 주세요")
			$('#pwd').focus();
			return false;
		}
		
		if(pwdChkVal == ""){
			alert("비밀번호를 입력해 주세요")
			$('#pwd').focus();
			return false;
		}
		/*비밀번호 확인*/
		if(pwdChkVal != pwdVal) {
			alert("비밀번호가 다릅니다.")
			$('#pwd').focus();
			return false;
		}
		 // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
		  // test(): 찾는 문자열이, 들어있는지를 알려준다.(boolean)
		if(!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/.test(pwdVal)){
	  		alert("비밀번호는 영문과 특수문자 숫자를 포함하여야 합니다."+"\n\8자 이상 20자 이하");
	  		return false;
		}
		// 특수문자 확인
		 for(let i = 0; i < pwd.length; i++){
		   if(/\W/.test(pwd.charAt(i))){ // 특수문자 중에서
		     if(!/[!@#$^*+=-]/.test(pwd.charAt(i))){ // 포함된 문자가 아니면
		       alert("비밀번호에 특수문자는 !@#$%^*+=-만 사용 가능합니다.");
		       $("#password").focus();
		       return false;
		      }
		    }
	 	}
		
		/*회원가입 데이터 전송*/
		let repData = $("#reg_form").serialize();
		
		  $.ajax({
	  	      type  : "POST",
	  	      url    : '/user/regist.do',
	  	      data: repData,
	  	      contentType : "application/x-www-form-urlencoded;charset=UTF-8",
	  	      success: function(result){
	  	    	alert("환영합니다. 로그인 페이지로 이동합니다.")
	  			location.href = "/user/loginForm.do";
	  	      },
	  	      error: function(xhr, status, error) {
	  	      	alert("통신실패");
	  	      }  
		  	}) 
	})
	
	$('#idCheck').click(function(){
		let checkId = $('#id').val();
		/*아이디 영어나 숫자만*/
		let idFormatChk = /^[a-zA-Z0-9]*$/;
			
		if($('#id').val() == ""){
			alert("아이디를 입력해 주세요")
			$('#id').focus();
			return false;
		}
		if(!idFormatChk.test(checkId)){
			alert("아이디는 영어와 숫자만 입력가능합니다.")
			return false;
		}
		if(checkId.length > 20 || checkId.length < 5){
			alert("아이디는 5 ~ 20자까지 입력 가능합니다.")
			return false;
		}
		/*입력한 id값 전송*/
		$.ajax({
			type : "GET",
			url : "/user/idCheck.do",
			data : { checkId : checkId
			},
			success:function(result){
				if(result=="ok"){
					alert("사용 가능한 아이디입니다.")
					return false;
				}else{
					alert("사용중인 아이디입니다.")
					
				}	
			},
			error: function(xhr, status, error){
				console.log(error)
				alert("오류발생")
			}
		})
		return false;
	})
	
	function moveLoginForm(){
		location.href = "/user/loginForm.do";
	}

	$(document).ready(function(){
		
	});
</script>
</html>