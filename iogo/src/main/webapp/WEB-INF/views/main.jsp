<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>국정감사 매인 페이지</title>
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

</script>

<style>
* {
	margin: 0px;
	padding: 0px;
}

.container {
	
	width: 1000px;
	height : 700px;
	margin: 100px auto;
	
}

.myPage {
	text-align: center;
	border: 1px solid #000; ;
	width : 80%;
	height : 10%;
}

.myPage div {
	width :100%;
	height :50%;
	border: 5px;
	vertical-align: middle;
}

.myPage .myBtn {
	height: 50%;
	text-align: right;
}

.mainform {
	margin-top : 20px;
	border: 1px solid #000; ;
	width : 80%;
	height : 50%;
}
.mainform span {
	display: inline-block;
	
}
</style>


</head>
<body>

	<div class="container">

		<div class="myPage">
			<div>
				<h3>${SESS_USER_NM }님(${SESS_UESR_ID }) 환영합니다.</h3>
			</div>
			
			<div class="myBtn">
				<button type="button" id="modifyBtn">회원정보수정</button> 
				<button type="button" id="logoutBtn">로그아웃</button> 
			</div>
			
		</div>

		
		<div class="mainform">
			
			<span><button>국정감사게시판</button></span> 
			<span><button>국정감사관리게시판</button></span> 
			
		</div>
		

	</div>
	
<script>
	 document.getElementById("modifyBtn").addEventListener("click", function() {
		 console.log("modify");
		 window.location.href = "/user/modify";
		  });
	 
	 $(document).ready(function() {
		  $("#logoutBtn").click(function() {
		    $.ajax({
		      url: "/logout",
		      type: "POST",
		      dataType: "json",
		      success: function(response) {
		    	  console.log(" 1 = 성공 / 0=실패");
		    	  console.log(response);
		    	  if(response == 1 ) {
		    		  console.log("로그아웃 성공");
		    		  window.location.replace("/login");
		    	  }  else {
		    		  console.log("로그아웃 실패");
		    		  location.reload();
		    	  }
		        // 여기에 로그아웃 성공 후 수행할 동작을 추가할 수 있습니다.
		      },
		      error: function(xhr, status, error) {
		        console.log("요청 실패");
		        // 여기에 로그아웃 실패 후 수행할 동작을 추가할 수 있습니다.
		      }
		    });
		  });
		});

</script>

</body>
	

</html>