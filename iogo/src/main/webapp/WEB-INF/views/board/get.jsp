<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>국정감사 게시판 상세페이지</title>
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
.mainTitle h3{
	margin-bottom : 13px;
}
.main {
	width : 60%;
	text-align: center;
	margin : auto;
}
.board {
	width:50%;
	margin : 10px auto;
}

.board .bContent {
	display: inline-block;
	width: 30%;
	text-align: left;
}
.board div {
	width:67%;
	display: inline-block;
	text-align: left;
}
.loginbutton button {
	margin : 10px auto;
	width : 60px;
}


</style>

</head>
<body>

	<div class="container">

		<div class="mainTitle">
			<h3>국정감사 게시판 상세페이지</h3>
		</div>

		<div>
		
			<div class="main">
			
				<div class="board" id="id">
					<div class="bContent">아이디 : </div> <div>${__GET__.id }</div>
				</div>
				<div class="board" id="name">
					<div class="bContent">이름 : </div> <div>${__GET__.name }</div>
				</div>
				<div class="board" id="dept">
					<div class="bContent">부서 : </div>
					<c:choose>
						<c:when test="${__GET__.dept =='A001'}"><div>운영부서</div></c:when>
						<c:when test="${__GET__.dept =='B001'}"><div>지원부서</div></c:when>
						<c:when test="${__GET__.dept =='C001'}"><div>인사부서</div></c:when>
					</c:choose> 
				</div>
				<div class="board" id="status">
					<div class="bContent">상태 : </div> 
					<div> 
						<c:choose>
							<c:when test="${__GET__.status ==0  }">요청중</c:when>
							<c:when test="${__GET__.status ==1  }">담당자지정</c:when>
							<c:when test="${__GET__.status ==2  }">처리완료</c:when>
							<c:when test="${__GET__.status ==3  }">보류</c:when>
						</c:choose> 
					</div>
				</div>
				<div class="board" id="title">
					<div class="bContent">제목 : </div> <div>${__GET__.title }</div>
				</div>
				<div class="board" id="content">
					<div class="bContent">내용 : </div> <div>${__GET__.content }</div>
				</div>
			
			</div>
			
			
			<div class="loginbutton">
				<button type="button" id="cancel" >닫기</button>
			</div>
		</div>
					

	</div>

<script>

	
	 $('#cancel').click(function() {
		 window.location.href= '/board/list';
		  });

	
</script>



</body>
</html>