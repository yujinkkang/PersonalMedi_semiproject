<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<style type="text/css">
</style>
</head>
<body>
	
	<%@ include file="/form/header.jsp" %>
	<link rel="stylesheet" href="assets/css/login_bg.css" />
	<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/login.css" />

<div id="page-wrapper">
	
	<section id="intro" class="wrapper style1">
	<div class="title">About Personal Medi</div>
	<div style="width: 40%; float:none; margin:0 auto;
	text-align: left; display: block;
	font-weight: 700; letter-spacing: -0.05em; font-size: 1.2em;">
	<img src="images/aboutImage.png" alt="" style="width: 100%"/>
	
	
	<img src="images/main_image.png" alt="" style="width:100%">

 	<p>
 	<img src="images/main_image.jpg" alt="" style="width:100%">
 	<b>Personal MEDI</b>는<br>
	건강 데이터를 관리하는데 도움을 줍니다.<br>
	<br>
	종이 처방전을 보관하거나 직접 기록하는 수고로움 없이<br>
	의료기록을 관리할 수 있습니다.<br>
	Personal MEDI와 함께<br>
	당신의 건강을 평생 관리해나갈 기반을 얻어가세요.
 	</p>
 	
	<img src="images/aboutImage02.jpg" alt="" style="width:100%">
 	<p>
 	Personal MEDI는 <br>					
	"내 손 안의 의료 데이터"를 목표로,<br> 
	서비스를 통해 모두가 자신의 의료 데이터를 <br>
	관리할 수 있는 환경을 만들어나가고 있습니다.<br><br>

	언제 어디서나 확인가능한 의료데이터, <br>
	Personal MEDI와 함께라면 가능합니다.<br>
 	</p>
 	
 	
 	<p>
 	<img src="images/main_image2.jpg" alt="" style="width:100%">
 	<h3>◆ Personal MEDI의 특별함</h3>
 	</p>
 	
 	<p>
	1. 의료 데이터를 한 눈에<br>
    &nbsp; &nbsp; : 검진 결과, 진료내역 등 의료 데이터를<br> 
    &nbsp; &nbsp; 그래프를 통해 간편하게 확인할 수 있습니다.<br><br> 
 	2. 데이터 입력은 간편하게<br>
    &nbsp; &nbsp; : XML/이미지 파일 업로드를 통해 <br>
    &nbsp; &nbsp; 의료 데이터를 입력할 수 있습니다.<br><br>
 	3. 유익한 정보도 함께<br>
    &nbsp; &nbsp; : 처방받은 약을 검색하고 안정성을 확인하실 수 있습니다.
    &nbsp; &nbsp; 그동안 모르고 먹었던 약들의 정보를 확인해보세요.
 	</p>
 	
 	
	</div>
	</section>

	<%@ include file="/form/footer.jsp" %>
	
</body>
</html>