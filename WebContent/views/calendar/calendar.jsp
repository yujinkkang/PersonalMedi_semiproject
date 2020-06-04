<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.med.calendar.dao.CalendarDao"%>
<%@page import="com.med.calendar.dto.CalendarDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel='stylesheet' type='text/css' href='resources/calendar/css/daygrid.css'>
<link rel='stylesheet' type='text/css' href='resources/calendar/css/free.css'>
<link rel='stylesheet' type='text/css' href='resources/calendar/css/fullcalendar.css'>
<link rel='stylesheet' type='text/css' href='resources/calendar/css/timegrid.css'>

<title>Personal MEDI</title>
<%@ include file="/form/header.jsp" %>
		<link rel="stylesheet" href="assets/css/calendar_bg.css"/>
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/calendar.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script><!-- 상단고정 -->
<script type="text/javascript" src="resources/calendar/js/fullcalendar.js"></script>
<script type="text/javascript" src="resources/calendar/js/daygrid.js"></script>
<script type="text/javascript" src="resources/calendar/js/interaction.js"></script>
<script type="text/javascript" src="resources/calendar/js/timegrid.js"></script>
<script type="text/javascript" src="resources/calendar/js/ko.js"></script>
<script type="text/javascript" src="resources/calendar/js/jquery.bpopup.min.js"></script>
<% 
	
	String member_id = request.getParameter("member_id");
	if(member_id == "") {
%>
	<script type="text/javascript">
		alert('로그인한 회원만 이용 가능한 메뉴입니다');
		history.go(-1);
	</script>
<%
	} else {

		int member_seq = (int)session.getAttribute("member_seq");	

		CalendarDao dao = new CalendarDao();
		List<CalendarDto> list = dao.checkMemberList(member_seq);
%>


<script type="text/javascript">


	document.addEventListener('DOMContentLoaded', function() {
		
		var dataset = [  /* 일정데이터를 풀캘린더 형식에 맞게 바꿔주는 코드  */
		    <c:forEach var="list" items="<%=list%>" varStatus="status">
		        <c:if test="${list.title != ''}">
		            {
		            "color": "#2C3E50" /* 일정바 색상, 변경가능 */
		            ,"title":'<c:out value="${list.getTitle()}" />'
		            ,"start":'<c:out value="${list.getStart_date()}" />'
		            ,"end":'<c:out value="${list.getEnd_date()}" />'+'T00:00:04'
		            
		            }<c:if test="${!status.last}">,</c:if>

		        </c:if>
		    </c:forEach>
		];
		
		
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins : [ 'interaction', 'dayGrid', 'timeGrid' ],
			defaultView : 'dayGridMonth',
			defaultDate : new Date(),
			header : {
				left : 'prev,next today', /* 왼쪽버튼 */
				center : 'title', /* 타이틀 위치 */
				right: 'addEventButton' /* 일정추가버튼 */
			},
			
			events: dataset, /* 일정들 */
			
			//locale:"ko"
			customButtons: { /* 일정추가 버튼 */
     			addEventButton: {
        		text: 'add schedule',
        		click: function ()
        		{
        			var htmlsContents = "";
        			htmlsContents += "<div style='width:100%; height:32px'><div style='width:30%;float:left; padding-left:18px; margin-bottom:8px;'>일정 명칭 : </div><div style='width:60%;float:right'><input type='text' id='calendar_title' value=''></div></div>";
        			htmlsContents += "<div style='width:100%; height:32px'><div style='width:30%;float:left; padding-left:18px; margin-bottom:8px;'>시작 날짜 : </div><div style='width:60%;float:right'><input type='text' id='calendar_start_date' value='' ></div></div>";
        			htmlsContents += "<div style='width:100%; height:32px'><div style='width:30%;float:left; padding-left:18px'>마침 날짜 : </div><div style='width:60%;float:right'><input type='text' id='calendar_end_date' value=''></div></div>";
        			htmlsContents += "<div style='width:100%; text-align:center; height:30px; margin-bottom:10px; margin-top:15px;'><button onclick=saveSchedule(); id='btn1'>저장하기</button></div>";
        			openPopup("일정 등록",htmlsContents, 400);
        		}
		      }
		    }
					
					
		});
		calendar.render();
	});
	
	
	function openPopup(subject,contents, widths) /* openPopup("일정 등록",htmlsContents, 400); */
	{
		$("#alert_subject").html(subject); /* 아이디가 alert_subject인 태그에 subject("일정 등록")내용 삽입 */
		$("#alert_contents").html(contents); /* 아이디가 alert_contents인 태그에 contents("htmlsContents")내용 삽입 */
		openMessage('winAlert',widths);
	}
	
	function saveSchedule()/* 저장하기 버튼을 누르면 다음이 실행됨 */
	{
		var titleStr = $("#calendar_title").val();
		var startStr = $("#calendar_start_date").val();
		var endStr = $("#calendar_end_date").val();
		
		var title = titleStr;
		var start = new Date(startStr + 'T00:00:00'); // will be in local time
        var end = new Date(endStr + 'T00:00:10');
		
		
        
            try {
	  			$.ajax({
	  						type : 'POST',
	  						url : 'cal?command=insertschedule',
	  						data : {
	  							title : title,
	  							start_date : startStr,
	  							end_date : endStr
	  						},
	  						success : function(data) {
	  							alert("일정전송완료");
	  							closeMessage('winAlert'); /* popup창 close */
	  							window.location.reload();/* 전송된 일정이 화면에 나타나게 하기 위해 reload함 */

	  						},
	  						error : function(XMLHttpRequest, textStatus,
	  								errorThrown) {
	  							alert("일정전송실패");
	  						}

	  					})
	  					
	  					
	  		} catch (e) {
	  			alert("잘못됨");
	  		}
          
	}

	function openMessage(IDS,widths) /* openMessage('winAlert',widths); */
	{
		$('#'+IDS).css("width",widths+"px");/* id가 winAlert인 태그에 css추가 */
		$('#'+IDS).bPopup(); /* 제이쿼리 bPopup();실행 */
	}

	function closeMessage(IDS)
	{
		$('#'+IDS).bPopup().close();
	}

</script>
</head>
<body class="homepage is-preload">
<%
	}
%>
	<div id="page-wrapper">
	
	<!-- 캘린더 -->
	<section id="intro" class="wrapper style1">
					<div class="title">내원 스케줄 관리</div>
					<div class="container">
					<div>
					당신의 스케쥴을 관리하세요!<br>
				 	달력, 내원 일자부터 약 복용 기간까지 의료와 관련된 정보들을 관리할 수 있습니다.<br>
				    웹알람을 통해 앞으로의 일정을 체크해보세요.
					</div>
						<p class="style1">
	
	<div class="out">
		<div class="in" id='calendar' style="width:800px"></div>
	</div>
	
	
	<!-- 일정등록 팝업창 -->
	<div class="box box-success" style="width:500px; display:none; background-color:white" id="winAlert">
	<div class="box-header with-border" style="background-color:white; padding-left:15px">
	  <h3 class="box-title" id="alert_subject" style="background-color:white"></h3>
	</div><!-- /.box-header -->
	<div class="box-body" id="alert_contents" style="font-size:15px;background-color:white">
		
	</div><!-- /.box-body -->
 </div><!-- /.box -->
			
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