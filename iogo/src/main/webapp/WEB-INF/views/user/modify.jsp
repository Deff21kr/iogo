<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>국정감사 회원수정 페이지</title>
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

.joinForm {
	display: inline-block;
	width: 60%;
}

.joinForm div {
	padding-top: 5px;
	padding-bottom: 5px;
}
.joinForm #id_check,#pw_check {
	padding-top: 3px;
	padding-bottom: 3px;
}
.join_check {
	font-size: 0.7rem;
	color: red;
}
.inputbox span {
	display:inline-block;
	width: 30%;
	text-align: left;
}
.inputbox input {
	display:inline-block;
	width:30%;
}
.inputbox select {
	display:inline-block;
	width:30%;
	text-align: center;
}
.loginbutton button {
	margin : 10px auto;
	width : 60px;
}


</style>

</head>
<body>

	<div class="container">

		<div>
			<h3>회원수정페이지</h3>
		</div>

		<div>
			<div class="joinForm">
					<div class="inputbox">
						<span>아이디 :</span> <input type="text" id="id" class="joininput" name="id" value="${SESS_UESR_ID }" readonly="readonly">
						<div id="id_check" class="check"></div>
					</div>
					
				
					<div class="inputbox">
						<span>이름 :</span> <input type="text" id="name" class="joininput" name="name" placeholder="이름을 입력하세요.">
						<div id="name_check" class="check"></div>
					</div>
					<div class="inputbox">
						<span>성별 :</span>
						<select id="gender" name="gender" required>
							<option value="">성별</option>
							<option value="M">남성</option>
							<option value="F">여성</option>
						</select>
					</div>
					<div class="inputbox">
						<span>권한 :</span> 
						<select id="auth" name="auth" required>
							<option value="" >권한</option>
							<option value="01">자료요청자</option>
							<option value="02">부서책임자</option>
							<option value="03">부서담당자</option>
						</select>
					</div>
					<div class="inputbox" >
						<span>부서 :</span>
						<select id="dept" name="dept" required>
							<option value="">부서</option>
							<option value="A001">운영부서</option>
							<option value="B001">지원부서</option>
							<option value="C001">인사부서</option>
						</select>
					</div>

			</div>
			
			<div class="loginbutton">
				<button type="button" id="reg_submit">등록</button>
				<button type="button" id="cancel" >취소</button>
			</div>
		</div>
					

	</div>

<script>

	$(document).ready(function() {
		  var inval_Arr = new Array(1).fill(false);
		  $("#auth").prop("disabled", true);
	      $("#dept").prop("disabled", true);
		  // 모든 공백 체크 정규식
		  var empJ = /\s/g;
		  // 아이디 정규식
		  var idJ = /^[a-zA-Z0-9]{8,16}$/;
		  // 비밀번호 정규식
		  var pwJ = /^[A-Za-z0-9]{8,20}$/;
		  // 이름 정규식
		  var nameJ = /^[가-힣A-Za-z]{2,10}$/;
		  var nickJ = /^[가-힣A-Za-z0-9]{2,10}$/;
		  // 이메일 검사 정규식
		  var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		  // 핸드폰 검사 정규식
		  var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;


		  //============== 이름 유효성 검사 ===============//
		  $("#name").blur(function() {
		    var name = $('#name').val();
		    if (nameJ.test(name)) {
		      $('#name_check').text('합격!');
		      $('#name_check').css('color', 'yellowgreen');
		      $("#auth").prop("disabled", false);
		      $("#dept").prop("disabled", false);
		      inval_Arr[0] = true;
		    } else {
		      $('#name_check').text('이름을 확인해주세요');
		      $('#name_check').css('color', 'red');
		      $("#auth").prop("disabled", true);
		      $("#dept").prop("disabled", true);
		      inval_Arr[0] = false;
		    }
		  });

		 
		  $('#reg_submit').click(function() {
		    var validAll = true;
		    var id = $("#id").val();
		    var name = $("#name").val();
		    var gender = $("#gender").val();
		    var auth = $("#auth").val();
		    var dept = $("#dept").val();
		    
		    
		    for (var i = 0; i < inval_Arr.length; i++) {

		      if (inval_Arr[i] == false) { // 유효성 검사를 하나라도 통과하지 못했다면
		        validAll = false;
		        console.log(i +" : "+inval_Arr[i]);
		      }
		    }

		    if (validAll) { // 유효성 모두 통과
		    	  $.ajax({
		    		    type: "POST",
		    		    url: "/user/modify",
		    		    data: {
		    		    	id: id,
				    		name: name,
				    		gender: gender,
				    		auth: auth,
				    		dept: dept
				    	  },
				    	dataType : "json",
		    		    success: function(response) {
		    		      if (response === 1) {
		    		        alert("회원수정 되었습니다.");
		    		        window.location.replace("/main");
		    		      } else {
		    		        alert("수정에 실패했습니다.");
		    		      }
		    		    },
		    		    error: function() {
		    		      // 에러 처리
		    		      alert("서버와의 통신에 실패했습니다.");
		    		    }
		    		  });
		    
		    } else {
		    	 
		      alert('항목을 올바르게 입력해주세요.');
		      return false;
		    }
		  });
		  
		  
		});

	
	 $('#cancel').click(function() {
		 window.location.href= '/main';
		  });

	
</script>



</body>
</html>