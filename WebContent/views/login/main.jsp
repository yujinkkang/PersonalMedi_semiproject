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
	<link rel="stylesheet" href="assets/css/index.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />

<div id="page-wrapper">
	
	<section id="intro" class="wrapper style1">
	<div class="title">About Personal Medi</div>
	<div style="width: 40%; float:none; margin:0 auto;
	text-align: left; display: block;
	font-weight: 700; letter-spacing: -0.05em; font-size: 1.2em;">
	<img src="images/mainImage.png" alt="" style="width: 100%"/>
	
	<p>당신의 건강은 우리 사회의 밝은 미래를 약속합니다.</p>
	<h2>당신의 정보는 당신에게 있나요?</h2>

 	<p><b>Personal MEDI</b>는 당신의  의료정보를 한 곳에 모아 표준화하고,<br>
 	언제 어디서든 한 눈에 볼 수 있는<br>
 	차별화된 개인 건강관리 솔루션을 제공하는 것을 목표로 합니다.</p>
 	
 	<p>
 	<img src="images/mainImage3.jpg" alt="" style="width: 100%"/>
 	</p>

 	<p>
 	주기적으로 받는 건강검진, 매일 복용하는 의약품들...<br>
 	당신의 건강을 위해 반드시 알고있어야 하면서도, <br>
 	알기 어려워서, 확인하기 귀찮아서 그냥 넘어가고 계시진 않으신가요? 
 	</p>
 	
 	<p>
 	서랍 속의 잠들어있는 당신의 건강정보를 깨우세요. <br>
 	손쉬운 등록을 통하여, 흩어진 당신의 건강정보를  <br>
 	한 눈에 볼 수 있게 해드립니다.
 	</p>
 	
 	<img src="images/mainImage2.png" alt="" style="width: 100%"/>
 	
	<p>
 	▶ 개인별 맞춤 건강관리 서비스 제공<br>
  	&nbsp;&nbsp;- 공단 및 민간의 건강검진 결과의 입력과 작성이 가능합니다.<br>
  	&nbsp;&nbsp;- 입력받은 건강검진 결과를 기초로하여, 시각화된 데이터를 제공합니다.<br>
  	&nbsp;&nbsp;- 개인의 검진 및 진료 스케줄을 관리합니다. 
  	</p>
  	  
	<p>
 	▶ 의약품 안전사용서비스 제공<br>
  	&nbsp;&nbsp;- 안전한 의약품 복용을 위하여, 환자의 복용 약품에 대한 정보를 입력받습니다.<br>
   	&nbsp;&nbsp;- 의약품 금기 및 안정성에 대한 정보를 제공합니다.  
   	</p>
  
  	<p>
	▶ 병의원, 약국 등 의료기관 정보 제공<br>
  	&nbsp;&nbsp;- 의료 이용 편의를 위한 진료과목, 주소, 전화번호 등의 정보를 제공합니다.
	</p>
	</div>
	</div>
	</section>

	<%@ include file="/form/footer.jsp" %>
</body>
</html>