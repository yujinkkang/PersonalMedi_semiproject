<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%request.setCharacterEncoding("UTF-8");%>
    <%response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
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
      <input type="hidden" name="command" value="writeres"/>
      <input type="hidden" name="member_seq" value="${loginDto.member_seq }"/>
      <table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="member_id" value="${loginDto.member_id }" readonly/></td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td><input type="text" name ="question_title"/></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td>
				<textarea class="form-control" id="p_content" name="question_content" cols=""></textarea>
				<script type="text/javascript">
				 CKEDITOR.replace('p_content'
				                , {height: 500                           
				                 });
				</script>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기"/>
					<input type="button" value="취소" onclick="location.href='question.do?command=list&page=1'" />
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