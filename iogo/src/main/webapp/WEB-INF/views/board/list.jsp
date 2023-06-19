<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>국정감사게시판</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

    <script>
        $(function () {
            $('#regBtn').click(function () {
                let currPage = "${pageMaker.cri.currPage}";
                location = "/board/qna/register?currPage="+currPage;
            });
            $('.pageNum').on('click', function(e) {
                let selectedPageNum = e.currentTarget.textContent;
                location = "/board/qna/list?currPage="+selectedPageNum;
            });
        });
       
    </script>
</head>

<body>
	<div class="container">
        <div class="title">
            <h3>국정감사게시판</h3>
        </div>

        <div class="search">

            <div class="searchStatus">상태 : 
                <select name="status" id="status">
                    <option value="">선택</option>
                    <option value="0">요청중</option>
                    <option value="1">담당자지정</option>
                    <option value="2">처리완료</option>
                    <option value="3">보류</option>
                </select>
            </div>

            <div class="searchTitle">제목 : 
               <input type="text" id="title" name="title" placeholder="입력">
            </div>

            <div class="btn">
                <button id="submitBtn">조회</button>
                <button type="button" onclick="location.href='/main'" >메인페이지</button>
            </div>
            
        </div>
            
        <div clasee="main">
            <table id="boardTable" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>아이디</th>
                        <th>제목</th>
                        <th>내용</th>
                        <th>상태</th>
                        
                    </tr>
                </thead>
                <tbody>
                
                </tbody>
             <!--     
                <c:forEach items="${__LIST__ }" var="board">
                    <tr>
                        <td>${board.bno}</td>
                        <td>${board.id}</td>
                        <td>
                            <a class='move' href='/board/get?bno='+${board.bno }>${board.title} </a>
                        </td>
                        <td>${board.content}</td>
                        <c:choose>
						      <c:when test="${board.status == '0' }">
						        <td>요청중</td>
						      </c:when>
						      <c:when test="${board.status == '1' }">
						        <td>담당자지정</td>
						      </c:when>
						      <c:when test="${board.status == '2' }">
						        <td>처리완료</td>
						      </c:when>
						      <c:when test="${board.status == '3' }">
						        <td>보류</td>
						      </c:when>
					    </c:choose>
                        
                    </tr>
                </c:forEach>
                -->
            </table>

        </div>

    </div>
 
 	<script>
 	$(document).ready(function() {
 	    $('#submitBtn').click(function() {
 	      var status = $('#status').val();
 	      var title = $('#title').val();
 	      
 	      $.ajax({
 	        url: '/board/list',
 	        type: 'GET',
 	        data: {
 	          status: status,
 	          title: title
 	        },
 	        success: function(response) {
 	           // 받아온 데이터를 처리하여 HTML에 추가하는 코드 작성
 	           var tableBody = $('#boardTable tbody'); // 테이블의 tbody 요소를 선택

 	           // 기존 테이블 내용 초기화
 	           tableBody.empty();

 	           // 받아온 데이터를 순회하면서 행을 생성하여 테이블에 추가
 	           for (var i = 0; i < response.length; i++) {
 	               var board = response[i];

 	               // 새로운 행(tr) 생성
 	               var newRow = $('<tr>');

 	               // 각 열(td)에 데이터 추가
 	               newRow.append('<td>' + board.bno + '</td>');
 	               newRow.append('<td>' + board.id + '</td>');
 	               newRow.append('<td><a class="move" href="/board/get?bno=' + board.bno + '">' + board.title + '</a></td>');
 	               newRow.append('<td>' + board.content + '</td>');
 	               newRow.append('<td>' + board.status + '</td>');

 	               // 생성한 행을 테이블에 추가
 	               tableBody.append(newRow);
 	           }
 	        },
 	        error: function() {
 	          // AJAX 요청 실패 시 처리할 코드
 	        }
 	      });
 	    });
 	  });
 	</script>
</body>

</html>