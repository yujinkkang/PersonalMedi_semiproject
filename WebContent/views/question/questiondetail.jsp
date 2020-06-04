<%@page import="com.med.login.dto.LoginDto"%>
<%@page import="com.med.question.dto.QuestionDto"%>
<%@page import="com.med.question.dao.QuestionDao"%>
<%@page import="com.med.question.dao.QuestionDaoImpl"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("UTF-8");%>
    <%response.setContentType("text/html; charset=UTF-8");%>
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

    <table border= "1">
      <tr>
        <th>작성자</th>
        <td>${dto.member_id }</td>
      </tr>
      <tr>
        <th>제목</th>
        <td>${dto.question_title }</td>
      </tr>
       <tr>
        <th>글 내용</th>
        <td><textarea rows="10" cols="60" readonly="readonly">${dto.question_content }</textarea></td>
      </tr>
       <tr>
        <td colspan="2">
<% 
if(session.getAttribute("member_id") !=null){%>
<% if(session.getAttribute("member_id").equals("admin")){ %> 			
			<input type="button" value="답변" onclick="location.href='question.do?command=question&question_boardno=${dto.question_boardno }'" />
<% } 
      QuestionDto dto = (QuestionDto)request.getAttribute("dto");
    if(session.getAttribute("member_id").equals(dto.getMember_id())){ %> 
            <input type="button" value="수정" onclick="location.href='question.do?command=update&question_boardno=${dto.question_boardno }'" />
			<input type="button" value="삭제" onclick="location.href='question.do?command=delete&question_boardno=${dto.question_boardno }'" />
			<input type="button" value="목록" onclick="location.href='question.do?command=list&page=1'" />
<%}
}
%>
			</td>
      </tr>
    
    </table>
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