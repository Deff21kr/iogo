<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>국정감사 로그인 페이지</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/resources/favicon.ico"
	type="image/x-icon">
<link rel="icon"
	href="${pageContext.request.contextPath }/resources/favicon.ico"
	type="image/x-icon">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/loginpage.css">
<!-- fonts awesome -->
<script src="https://kit.fontawesome.com/1486713bf1.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

<script>


	$(document).ready(function() {
		  var inval_Arr = new Array(2).fill(false);
		  var empJ = /\s/g;
		  var pwJ = /^[A-Za-z0-9]{8,20}$/;
		  var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		  
		$('#join').click(function() {
			  window.location.href= '/join';
			});
		  
		  $('#reg_submit').click(function() {
		    var password = $('#password').val();
		    var id = $('#id').val();
		    
		    // 비밀번호 유효성 검사
		    if (password == "") {
		      $('#login_check').text('비밀번호를 입력해주세요.');
		      inval_Arr[0] = false;
		      return false;
		    } else if (!pwJ.test(password)) {
		      $('#login_check').text("비밀번호는 영어 대/소문자와 숫자 8~20자리입니다.");
		      inval_Arr[0] = false;
		      return false;
		    } else {
		      $('#login_check').text("");
		      inval_Arr[0] = true;
		    }
		    
		    // 이메일 유효성 검사
		    if (mailJ.test(id)) {
		      $('#login_check').text("");
		      inval_Arr[1] = true;
		    } else {
		      $('#login_check').text('아이디를 확인해주세요.');
		      inval_Arr[1] = false;
		      return false;
		    }
	
		    var validAll = true;
		    for (var i = 0; i < inval_Arr.length; i++) {
		      if (inval_Arr[i] == false) {
		        validAll = false;
		        console.log(i + " : " + inval_Arr[i]);
		      }
		    }
		    
		    if (validAll) {
		      $("#reg_submit").attr("disabled", false);
		      console.log("유효성 검사 통과");
		    } else {
		      // 유효성 검사를 통과하지 못한 경우
		      alert('아이디 또는 비밀번호가 틀립니다.');
		      return false;
		    }
		  });
		});
		

</script>

<style>
* {
	margin: 0px;
	padding: 0px;
}

.container {
	text-align: center;
	width: 1000px;
	margin: 100px auto;
}

.loginform {
	display: inline-block;
	width: 40%;
}

.loginform div {
	padding-top: 5px;
	padding-bottom: 5px;
}
.loginform #id_check,#pw_check {
	padding-top: 3px;
	padding-bottom: 3px;
}
#login_check {
	font-size: 0.7rem;
	color: red;
}
</style>

</head>
<body>

	<div class="container">

		<div>
			<h3>국정감사 게시판 로그인 페이지</h3>
		</div>

		<div>
			<div class="loginform">

				<form action="/login" method="post">

					<div class="inputbox">
						아이디 : <input type="text" id="id" class="logininput" name="id" placeholder="아이디를 입력하세요.">
					</div>
					<div class="inputbox">
						비밀번호 : <input type="password" id="password" class="logininput" name="pw" placeholder="비밀번호를 입력하세요.">
					</div>
          			<div id="login_check">
          				${__RESULT__}
          			</div>
					
					<div class="loginbutton">
						<button id="reg_submit">로그인</button>
						<button type="button" id="join" >회원가입</button>
					</div>

				</form>
				
			</div>


		</div>

	</div>



</body>
</html>