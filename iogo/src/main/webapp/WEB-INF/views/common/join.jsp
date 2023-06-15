<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>국정감사 회원가입 페이지</title>
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
</style>

</head>
<body>

	<div class="container">

		<div>
			<h3>회원가입페이지</h3>
		</div>

		<div>
			<div class="joinForm">

					<div class="inputbox">
						<span>아이디 :</span> <input type="text" id="id" class="joininput" name="id" placeholder="아이디를 입력하세요.">
						<div id="id_check" class="check"></div>
					</div>
					
					<div class="inputbox">
						<span>비밀번호 :</span> <input type="password" id="password" class="joininput" name="pw" placeholder="비밀번호를 입력하세요.">
						<div id="pw_check" class="check"></div>
					</div>
					<div class="inputbox">
						<span>비밀번호 확인 :</span> <input type="password" id="password2" class="joininput" placeholder="비밀번호를 입력하세요.">
						<div id="pw_check2" class="check"></div>
					</div>
					<div class="inputbox">
						<span>이름 :</span> <input type="text" id="name" class="joininput" name="name" placeholder="이름을 입력하세요.">
						<div id="name_check" class="check"></div>
					</div>
					<div class="inputbox">
						<span>성별 :</span>
						<select name="gender" required>
							<option value="">성별</option>
							<option value="M">남성</option>
							<option value="F">여성</option>
						</select>
					</div>
					<div class="inputbox">
						<span>권한 :</span> 
						<select name="auth" required>
							<option value="" >권한</option>
							<option value="01">자료요청자</option>
							<option value="02">부서책임자</option>
							<option value="03">부서담당자</option>
						</select>
					</div>
					<div class="inputbox" >
						<span>부서 :</span>
						<select name="dept" required>
							<option value="">부서</option>
							<option value="A001">운영부서</option>
							<option value="B001">지원부서</option>
							<option value="C001">인사부서</option>
						</select>
					</div>

			</div>
			
			<div class="loginbutton">
				<button id="reg_submit">등록</button>
				<button id="cancel" >취소</button>
			</div>

		</div>

	</div>

<script>

	$(document).ready(function() {
		  var inval_Arr = new Array(4).fill(false);

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

		  //============== ID 중복 및 유효성 검사 ===============//
		  $("#id").blur(function() {
		    var id = $('#id').val();

		    if (id == "") {
		      $('#id_check').text('아이디를 입력바랍니다.');
		      $('#id_check').css('color', 'red');
		      inval_Arr[0] = false;
		      return;
		    }
		    if (!mailJ.test(id)) {
		      $('#id_check').text("영어 대/소문자와 숫자 8~16자리");
		      $('#id_check').css('color', 'red');
		      inval_Arr[0] = false;
		      return;
		    }
			
		    $.ajax({
		        url: '/idcheck?id='+id,
		        type: 'get',
		        dataType: 'json',
		        success: function(data) {
		            console.log("(성공 = 1) ==> " + data);

		            if (data == 1) {
		                $("#id_check").text("합격!");
		                $("#id_check").css("color", "yellowgreen");
		                inval_Arr[0] = true;
		            } else {
		                $("#id_check").text("중복된 아이디입니다.");
		                $("#id_check").css("color", "red");
		                inval_Arr[0] = false;
		            }
		        },
		        error: function() {
		            console.log("실패");
		        }
		    });
		  }); // 아이디 유효성 및 중복 검사

		  //============== 비밀번호 유효성 검사 ===============//
		  $("#password, #password2").blur(function() {
		    var password = $('#password').val();
		    var password2 = $('#password2').val();

		    // 비밀번호 유효성 검사
		    if (password == "") {
		      $('#pw_check').text('비밀번호를 입력해주세요.');
		      $('#pw_check').css('color', 'red');
		      inval_Arr[1] = false;
		    } else if (!pwJ.test(password)) {
		      $('#pw_check').text("영어 대/소문자와 숫자 8~20자리");
		      $('#pw_check').css('color', 'red');
		      inval_Arr[1] = false;
		    } else {
		      $('#pw_check').text("합격!");
		      $('#pw_check').css('color', 'yellowgreen');
		      inval_Arr[1] = true;
		    }

		    // 비밀번호 확인 검사
		    if (password2 == "") {
		      $('#pw_check2').text("비밀번호를 확인해주세요");
		      $('#pw_check2').css('color', 'red');
		      inval_Arr[2] = false;
		    } else if (password != password2) {
		      $('#pw_check2').text("입력하신 비밀번호가 다릅니다.");
		      $('#pw_check2').css('color', 'red');
		      inval_Arr[2] = false;
		    } else {
		      $('#pw_check2').text("합격!!");
		      $('#pw_check2').css('color', 'yellowgreen');
		      inval_Arr[2] = true;
		    }
		  });

		  //============== 이름 유효성 검사 ===============//
		  $("#name").blur(function() {
		    var name = $('#name').val();
		    if (nameJ.test(name)) {
		      $('#name_check').text('합격!');
		      $('#name_check').css('color', 'yellowgreen');
		      inval_Arr[3] = true;
		    } else {
		      $('#name_check').text('이름을 확인해주세요');
		      $('#name_check').css('color', 'red');
		      inval_Arr[3] = false;
		    }
		  });

		  //============== 이메일 유효성 검사 ===============//
		  $("#EMail").blur(function() {
		    var EMail = $('#EMail').val();
		    if (mailJ.test(EMail)) {
		      $('#mail_check').text('인증번호를 입력해주세요!');
		      $('#mail_check').css('color', 'yellowgreen');
		    } else {
		      $('#mail_check').text('이메일을 확인해주세요');
		      $('#mail_check').css('color', 'red');
		    }
		  });

		//   ============ 이메일 인증 ===================
              let code = ''; 
		  $('#mail_Check_Btn').click(function() {
				const EMail = $('#EMail').val(); // 이메일 주소값 얻어오기!
				console.log('완성된 이메일 : ' + EMail); // 이메일 오는지 확인
				const checkInput = $('#mail_Check_Input') // 인증번호 입력하는곳 
	

				$.ajax({
					type : 'get',
					url : "${pageContext.request.contextPath}/common/mailCheck?EMail="+EMail, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
                          
					success : function (data) {
						console.log("data : " +  data);
						checkInput.attr('disabled',false);
						code =data;
						alert('인증번호가 전송되었습니다.')
					}			
				}); // end ajax
			}); // end send eamil
			
			$('#mail_Check_Input').blur(function () {
				const inputCode = $('#mail_Check_Input').val();
				const $resultMsg = $('#mail_check_num');
				
				if(inputCode == code){
					$resultMsg.html('인증번호가 일치합니다.');
					$resultMsg.css('color','green');
					$('#mail_Check_Btn').attr('disabled',true);
					$('#EMail').attr('readonly',true);
					
				}else{
					$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
					$resultMsg.css('color','red');
				}
			});
		  

		  $('#reg_submit').click(function() {
			 
		    var validAll = true;
		    for (var i = 0; i < inval_Arr.length; i++) {

		      if (inval_Arr[i] == false) { // 유효성 검사를 하나라도 통과하지 못했다면
		        validAll = false;
		        console.log(i +" : "+inval_Arr[i]);
		      }
		    }

		    if (validAll) { // 유효성 모두 통과
		    	 $("#reg_submit").attr("disabled", false);
		    } else {
		    	 
		      alert('기각');
		      return false;
		    }
		  });
		});


	
</script>



</body>
</html>