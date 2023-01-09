<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/user/loginForm.css'/>" />
<title>home</title>
</head>
<body>
    <div class="container">
        <header>
            <h2>Login</h2>
        </header>

        <form method="post" id="login_form">
            <div class="input-box">
                <input type="text" id="id" name="id" placeholder="아이디">
                <label for="id">아이디</label>
            </div>

            <div class="input-box">
                <input type="password" id="pwd" name="pwd" placeholder="비밀번호">
                <label for="pwd">비밀번호</label>
            </div>
            <div class="btn_container">
                <button type="button" id="login_btn">로그인</button>
                <button  type="button" onclick="moveRegister();">회원가입</button>
            </div>
        </form>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript">
	
	function moveRegister(){
		location.href = "/user/registerPage.do";
	}
	
	$('#login_btn').click(function(){
		
		inputId = $("#id").val();
		inputPwd = $("#pwd").val();
		
		if(inputId == ""){
			alert("아이디를 입력해주세요.")
			$("#id").focus();
			return false;
		}
		if(inputPwd == ""){
			alert("비밀번호를 입력해주세요.")
			$("#pwd").focus();
			return false;           
        }
		
		let loginData = $("#login_form").serialize();
		
		$.ajax({
			type : "POST",
			url : "/user/login.do",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : loginData,
			success:function(result){
				if(result=="idCheck"){
					alert("아이디를 확인해 주세요.")
					return false;
				}else if(result=="pwdCheck"){
					alert("비밀번호를 확인 해 주세요")
					return false;
				}else if(result=="ok"){
					alert("환영합니다!")
					location.href = "/board/boardList.do?pageFilter=10";
				}	
			},
			error: function(xhr, status, error){
				console.log(error)
				alert("오류발생")
			}
		})
	})
	
	
	
</script>
</html>