<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>국정감사 관리게시판</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

   
</head>

<body>
	<div class="container">
        <div class="title">
            <h3>국정감사 관리게시판</h3>
        </div>

        <div class="search">
			
	
	            <div class="searchTitle">부서 : 
	               <input type="text" id="title" name="title" value="<%
																        if (session.getAttribute("SESS_DEPT_CD") != null) {
																            if (session.getAttribute("SESS_DEPT_CD").equals("A001")) {
																                out.print("운영부서");
																            } else if (session.getAttribute("SESS_DEPT_CD").equals("B001")) {
																                out.print("지원부서");
																            } else if (session.getAttribute("SESS_DEPT_CD").equals("C001")) {
																                out.print("인사부서");
																            }
																        }
																    %>" readonly="readonly">
	            </div>
	
	            <div class="btn">
	            	<form action="/board/admin/list" method="get">
	            		 <button id="submitBtn">조회</button>
	            		 <input type="hidden" name="dept" value="${SESS_DEPT_CD }" >
	            	</form>
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
                
                  <c:forEach items="${__ADMIN__ }" var="board">
                    <tr>
                        <td>${board.bno}</td>
                        <td>${board.id}</td>
                        <td>
                            <a class='move' href='/board/admin/get?bno=${board.bno }'>${board.title} </a>
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
                
             <!--    <tbody id="boardTableBody">
			   		 여기에 데이터가 동적으로 추가될 예정입니다
			    </tbody> -->
            </table>

        </div>

    </div>
 
 	<script>
 /* 	$(document).ready(function() {
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
 	            dataType: 'json',
 	            success: function(response) {
 	                var tbody = $('#boardTableBody');
 	                tbody.empty(); // 기존 데이터 삭제

 	                $.each(response.list, function(index, item) {
 	                    var row = '<tr>' +
 	                        '<td>' + item.bno + '</td>' +
 	                        '<td>' + item.id + '</td>' +
 	                        '<td>' + item.title + '</td>' +
 	                        '<td>' + item.content + '</td>' +
 	                        '<td>' + item.status + '</td>' +
 	                        '</tr>';
 	                    tbody.append(row);
 	                });
 	            },
 	            error: function() {
 	                // AJAX 요청 실패 시 처리할 코드
 	            }
 	        });
 	    });
 	}); */

 	</script>
</body>

</html>