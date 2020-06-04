<%@page import="com.med.calendar.dto.CalendarDto"%>
<%@page import="java.util.List"%>
<%@page import="com.med.calendar.dao.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="sessionLogin" value="${sessionScope.loginDto }"></c:set>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi5</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/index.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
</head>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script><!-- 상단고정 -->

<body class="homepage is-preload">
		<div id="page-wrapper">
<%--
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sessionLoginMember" value="${sessionScope.loginMember }"></c:set>
<c:set var="sessionLoginKakao" value="${sessionScope.loginKakao }"></c:set>
<c:set var="sessionLoginNaver" value="${sessionScope.loginNaver }"></c:set>
컨트롤러: session = request.getSession();
session.setAttribute

 --%>
				
		<!-- Header -->
				<section id="header" class="wrapper">

					<!-- Logo -->
					<div id="logo">
						<a href="logincontroller.do?command=main"><img id="logo1" src="images/logo_01.png" alt=""></a>
						
					</div>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li id="navi_00" class="current"><a href="cal?command=calendarMain"><div class="navi_7" id="navi_1"></div>진료</a>
								<ul>
									
									<li><a href="cal?command=calendar&member_id=${loginDto.member_id }">내원 스케줄 관리</a></li>
								
								</ul>
								
								</li>
								<li id="navi_01">
									<a href="checkcontroller.do?command=checkdetail&member_seq=${loginDto.member_seq }&member_id=${loginDto.member_id }"><div id="navi_2"></div>내 건강</a>
									<ul>
										<li><a href="checkcontroller.do?command=insert_type1">건강검진입력</a></li>
										<li><a href="checkcontroller.do?command=insert_type2">개인입력</a></li>
										<li><a href="checkcontroller.do?command=insert_type3">정밀입력</a></li>
										<li>
											<a href="checkcontroller.do?command=checkdetail&member_seq=${loginDto.member_seq }&member_id=${loginDto.member_id }">검진 결과 보기</a>
											
										</li>
										
									</ul>
								</li>
								<li id="navi_02"><a href="medcontroller.do?command=medicineList"><div id="navi_3"></div>처방전</a>
								<ul>
									<li><a href="medcontroller.do?command=medicineList">약 검색 / 등록 / 안정성 확인</a></li>
									
								</ul>
								</li>
								<li id="navi_03"><a href="logincontroller.do?command=locationSearch"><div id="navi_4"></div>병원 약국 정보</a>
									<ul>
										<li><a href="hospitalService.do?command=hospitalList">병원 조회</a></li>
										<li><a href="PharmService.do?command=pharmList">약국 조회</a></li>
										
									</ul>
								</li>
								<li id="navi_04"><a href="question.do?command=list&page=1"><div id="navi_5"></div>고객센터</a>
									<ul>
										<li><a href="question.do?command=faq">faq : 자주 하는 건강 질문</a></li>
										<li><a href="question.do?command=list&page=1">Q & A</a></li>
										
									</ul>
								</li>
								<li id="navi_05">
								
								<c:if test="${!empty loginDto }">
								<a href="logincontroller.do?command=logout"><div id="navi_6"></div>로그아웃</a>
								<script type="text/javascript">
									window.addEventListener('load', function() {
									   
										   var iconDataURI ="https://st4.depositphotos.com/6760872/31470/v/450/depositphotos_314707736-stock-illustration-white-bell-icon-isolated-with.jpg";
										   var titlemessage = document.getElementById('title').value;
										   var bodymessage = document.getElementById('start_date').value;
										   
										   var now = new Date();
										   var then = new Date(bodymessage);
										   var gap = then.getTime() - now.getTime();
										   gap = Math.floor(gap / (1000 * 60 * 60 * 24));
										   
										   var options = {
												    body: "D-day "+gap + " 일",
												    icon: iconDataURI
											}
										   
										   if (Notification && Notification.permission === "granted") {
										            // 사용자가 웹 알람 설정 허용해
										            var n = new Notification(titlemessage,options);
										            
										   } else {
											   alert("권한을 허용하면 알람이 표시됩니다.");
										   }
									   
								   })
								</script>
									<c:forEach var="calList" items="${calList }" varStatus="status">
			
										<input type="hidden" id="start_date" value="${calList.getStart_date() }">
										<input type="hidden" id="title" value="${calList.getTitle() }">
										
									</c:forEach>
								</c:if>
								<c:if test="${empty loginDto }">
									<a href="logincontroller.do?command=loginclick"><div id="navi_6"></div>로그인</a>
								</c:if>
								
								</div>
								</li>
									
								
							</ul>
                        </nav>

				</section>

				
				<div id="stickyn">
					<ul>
						<li id="navi_00" class="current"><a href="cal?command=calendarMain"><div class="navi_7" id="navi_1"></div>진료</a>
						<ul class="stickysub1">
							
							<li><a href="cal?command=calendar&member_id=${loginDto.member_id }">내원 스케줄 관리</a></li>
						</ul>
						
						</li>
						<li id="navi_01">
							<a href="checkcontroller.do?command=checkdetail&member_seq=${loginDto.member_seq }&member_id=${loginDto.member_id }"><div id="navi_2"></div>내건강</a>
							<ul class="stickysub2">
								<li><a href="checkcontroller.do?command=insert_type1">건강검진 입력</a></li>
								<li><a href="checkcontroller.do?command=insert_type2">개인 입력</a></li>
								<li><a href="checkcontroller.do?command=insert_type3">정밀 입력</a></li>
								<li><a href="checkcontroller.do?command=checkdetail&member_seq=${loginDto.member_seq }&member_id=${loginDto.member_id }">검진결과 보기</a></li>
							</ul>
						</li>
						<li id="navi_02"><a href="medcontroller.do?command=medicineList"><div id="navi_3"></div>처방전</a>
						<ul class="stickysub3"><li><a href="medcontroller.do?command=medicineList">약 검색 / 등록 / 안정성 확인</a></li></ul>
						</li>
						<li id="navi_03"><a href="#"><div id="navi_4"></div>병원 약국 찾기</a>
							<ul class="stickysub4">
								<li><a href="hospitalService.do?command=hospitalList">병원 조회</a></li>
								<li><a href="PharmService.do?command=pharmList">약국 조회</a></li>
								
							</ul>
						</li>
						<li id="navi_04"><a href="#"><div id="navi_5"></div>고객센터</a>
							<ul class="stickysub5">
								<li><a href="question.do?command=faq">faq : 자주 하는 건강 질문</a></li>
								<li><a href="question.do?command=list&page=1">Q & A</a></li>
								
							</ul>
						</li>
						
						<li id="navi_05"><a href="logincontroller.do?command=main"><div id="navi_7"></div>HOME</a></li>
					</ul>
				</div>
		
</body>

<script type="text/javascript">

window.addEventListener('load',function(){
    $("#webnoti").trigger("click");
});

</script>
			
		


</html>