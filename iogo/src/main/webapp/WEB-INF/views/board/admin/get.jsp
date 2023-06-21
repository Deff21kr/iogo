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
				<input type="hidden" id="bno" value=${__GET__.bno }>
				<div class="board" id="id">
					<div class="bContent">아이디 : </div> <div class="bResult">${__GET__.id }</div>
				</div>
				<div class="board" id="name">
					<div class="bContent">이름 : </div> <div class="bResult">${__GET__.name }</div>
				</div>
				<div class="board" id="dept">
					<div class="bContent">부서 : </div> 
					<c:choose>
						<c:when test="${__GET__.dept =='A001'}"><div class="bResult" id="bDept">운영부서</div></c:when>
						<c:when test="${__GET__.dept =='B001'}"><div class="bResult" id="bDept">지원부서</div></c:when>
						<c:when test="${__GET__.dept =='C001'}"><div class="bResult" id="bDept">인사부서</div></c:when>
					</c:choose> 
				</div>
				<div class="board" id="dept">
					<div class="bContent">담당자 : </div> 
					<div> 
						<c:choose>
							<c:when test="${__GET__.status =='0'}">
								<select name="id" id ="master">
								</select> 
							</c:when>
							<c:otherwise>${__GET__.sup }</c:otherwise>
						</c:choose>
					</div>
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
				<c:if test="${SESS_AUTH_CD == '02' }">	<!-- 세션의 권한의 부서책임자/02 이고 -->
					<c:if test="${__GET__.status == '0' }">	<!-- 게시글의 상태가 요청중/0 일때 -->
						<button type="button" id="select" >담당자지정</button>
					</c:if>
				</c:if>
				<button type="button" id="cancel" >닫기</button>
			</div>
		</div>
					

	</div>

<script>
	
		$(document).ready(function() {
		     var dept = document.getElementById("bDept").textContent;
		     console.log(" 변수 : " +dept);
		     $.ajax({
		         url: '/board/admin/select',
		         type: 'POST',
		         data: {
		        	 dept: dept,
		         },
		         dataType: 'json',
		         success: function(response) {
		        	 console.log("response: "+response);
		         	 var select = $('#master');
		             select.empty(); // 기존 데이터 삭제
		             var row2 = "<option value=''>" +
	            	   " 담당자를 선택해주세요. "+
	            	   "</option>";
	                     select.append(row2);
		             $.each(response, function(index, item) {
		            	 console.log(item.id);
		            	 console.log(item.name);
		            	 var row = "<option value='" + item.id + "'>" +
		            	   item.id + " : " + item.name +
		            	   "</option>";
		                     select.append(row);
		             }); 
		         },
		         error: function(XHR, status, error) {
		        	 console.log(XHR);
		        	 console.log(status);
		        	 console.log(error);
		         }
		     });
		});
	
	 $('#cancel').click(function() {
		 window.location.href= '/board/admin/list';
		  });
	 
	 $('#select').click(function() {
		 var sup = $('#master').val();
		 var bno = $('#bno').val();
		 console.log("sup : " +master+"!");
		 console.log("bno : " +bno+"!");
		 if(sup==null || sup==''){
			 alert("담당자를 지정해주세요.");
		 } else {
			 $.ajax({
		         url: '/board/admin/modify',
		         type: 'POST',
		         data: {
		        	 sup: sup,
		        	 bno: bno
		         },
		         dataType: 'json',
		         success: function(response) {
		        	console.log(response);
		        	if(response ==1 ) {
		        		alert("담당자가 지정되었습니다.");
		        		window.location.href='/board/admin/list';
		        	} else {
		        		window.location.reload(true);
		        	}
		           
		         },
		         error: function(XHR, status, error) {
		        	 console.log(XHR);
		        	 console.log(status);
		        	 console.log(error);
		         }
		     }); // ajax
			 
		 } // if-else
		 
	 }); // jquery
	
</script>



</body>
</html>