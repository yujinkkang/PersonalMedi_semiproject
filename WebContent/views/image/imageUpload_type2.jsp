<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<%@ include file="/form/header.jsp" %>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/sub_04_03_bg.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/sub_04_01.css" />
			<!-- Intro -->
<div id="page-wrapper">
	
				<section id="intro" class="wrapper style1">
					<div class="title"> 이미지 올리기</div>
					<div class="container">
					<div>
					사진 파일을 업로드해 주세요.
					</div>
						<p class="style1">
						<div class="bg">

<style>
#fileUpload{
	color: white;
	font-size: 12pt;
	border: 1px solid #f06b37;
}
</style>
	<form action="imageupload.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="insertImage_type2">
		<input type="file" name="filename" size=40 id="fileUpload">
		<br><br>
		<input type="submit" value="이미지 올리기" id="imageSubmit" style="font-size: 10pt;">
	</form>

<script type="text/javascript">

	$(document).ready(function(){
		$("#imageSubmit").attr("disabled", true);
	});
	
	document.getElementById('fileUpload').addEventListener('change', function () {
		$("#imageSubmit").attr("disabled", false);
	});

</script>
</div>
	
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