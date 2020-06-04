<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
</head>
<body class="homepage is-preload">
<%@ include file="/form/header.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/qna_bg.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/qna.css" />
		<div id="page-wrapper">
		<!-- Intro -->
				<section id="intro" class="wrapper style1">
					<div class="title"> Q & A</div>
					<div class="container">
						<p class="style1">
		
	<form action="question.do" method="post">
		<input type="hidden" name="command" value="list">
		<input type="hidden" name="page" value="1">
			
		<table>
			    
	<!-- 	<tr>
			<td colspan="4">
				<input type="checkbox" value="myList"> 내가 쓴 글 보기
			</td>
		</tr>   -->
			      
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
			    
		<c:choose>
		
			<c:when test="${empty list }">
			<tr>
			<td colspan="4">---작성된 글이 존재하지 않습니다---</td>
			</tr>
			</c:when>
			      
			<c:otherwise>
				<c:forEach items="${list }" var="list">
					<tr>
						<td>
							<c:forEach begin="1" end="${list.question_titletab }">
							ㄴ
							</c:forEach>
							<a href="question.do?command=detail&question_boardno=${list.question_boardno }">${list.question_title }</a>
						</td>
						<td>${list.member_id }</td>
						<td>${list.question_regdate }</td>
						<td>${list.question_readcount }</td>
					</tr>
				</c:forEach>
				
				<tr>
					<td colspan="4" align="center">
						<a href="question.do?command=list&page=1&option=${option }&search=${search }">◁◁</a>
						<a href="question.do?command=list&page=${prevNum }&option=${option }&search=${search }">◀</a>
											
						<c:forEach var="i" begin="${groupStartNum }" end="${groupEndNum }" step="1">
							<a href="question.do?command=list&page=${i }&option=${option }&search=${search }">
							
							<c:choose>
								<c:when test="${page eq i }">
								 <u><b>${i }</b></u>
								</c:when>
								
								<c:otherwise>
								${i }
								</c:otherwise>
							</c:choose>
							
							</a>
						</c:forEach>

						<a href="question.do?command=list&page=${nextNum }&option=${option }&search=${search }">▶</a>
						<a href="question.do?command=list&page=${totalPage }&option=${option }&search=${search }">▷▷</a>
			
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="button" value="글 작성하기" onclick="location.href='question.do?command=writeForm'">
					</td>
				</tr>
				
			</c:otherwise>
		
		</c:choose>
			    		
		<tr>
			<td colspan="4">
				<select name="option">
					<option value="0">제목</option>
					<option value="1">내용</option>
					<option value="2">제목+내용</option>
				</select>
				<input type="text" name="search">
				<input type="submit" value="검색">
			</td>
		</tr>
		</table>
	</form>
	</p>

						<p class="style2">
							<br class="mobile-hide" />
							 <a href="" class="nobr">Personal MEDI</a><br>
		
							 
							 
						</p>

						<p class="style3">Online Doctor<strong></strong><strong></strong><strong></strong>
						<a href=""></a>
						</p>
						
					</div>
				</section>
	
<%@ include file="/form/footer.jsp" %>
</body>
</html>