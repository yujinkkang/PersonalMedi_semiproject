<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<%
	String checkup_type = request.getParameter("checkup_type");
%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	$(function() {
		var checkup_type = ${checkup_type};
		
		//처음 페이지 로딩되었을 때
		if (checkup_type == 1) {
			$("#formType_1").show();
			$("#formType_2").hide();
			$("#formType_3").hide();
		} else if (checkup_type == 2) {
			$("#formType_1").hide();
			$("#formType_2").show();
			$("#formType_3").hide();
		} else if (checkup_type == 3) {
			$("#formType_1").hide();
			$("#formType_2").hide();
			$("#formType_3").show();
		}
		
		$("input[name=checkup_type]").click(function() {
			var type = $(this).val();
			$.ajax({
				type: 'POST',
				url: 'checkcontroller.do?command=insert_type' + type,
				success: function() {
					if (type == 1) {
						$("#formType_1").show();
						$("#formType_2").hide();
						$("#formType_3").hide();
					} else if (type == 2) {
						$("#formType_1").hide();
						$("#formType_2").show();
						$("#formType_3").hide();
					} else if (type == 3) {
						$("#formType_1").hide();
						$("#formType_2").hide();
						$("#formType_3").show();
					}
				}
			});
		})

	});
	//유효성(숫자만 입력)
	function checkNum(e) {
        var keyVal = event.keyCode;
 
        if(((keyVal >= 48) && (keyVal <= 57))){
            return true;
        }
        else{
            alert("숫자만 입력가능합니다");
            return false;
        }
    }
	
	//유효성(빈칸막기_type1만 적용:2,3에 빈칸이 있어도 checkdetail 작동)
	function check(){
		var checkup_type = ${checkup_type};
		var orderCnt = $('#inputGroupSelect01').val();
        var orderName = $('#fill2').val();
        var orderLocation = $('#fill3').val();
        var orderPhone = $('#fill4').val();
        
        var checkup_regist =  $('#checkup_regist').val();//주민등록번호
        var checkup_height =  $('#checkup_height').val();//신장
        var checkup_weight =  $('#checkup_weight').val();//체중
        var checkup_leye =  $('#checkup_leye').val();//시력좌
        var checkup_reye =  $('#checkup_reye').val();//시력우
        var checkup_lhear =  $('#checkup_lhear').val();//청각좌
        var checkup_rhear =  $('#checkup_rhear').val();//청각우
        var checkup_maxblood =  $('#checkup_maxblood').val();//혈압최고
        var checkup_minblood =  $('#checkup_minblood').val();//혈압최저
        var checkup_hemo =  $('#checkup_hemo').val();//혈색소
        var checkup_beforesugar =  $('#checkup_beforesugar').val();//식전혈당
		
		if(checkup_type= 1){
			if(checkup_regist==""){
				alert("주민등록번호를 입력해주세요.");
				 $('#checkup_regist').focus();
				 return false;
			}else if(checkup_height==""){
				alert("신장을 입력해주세요.");
				 $('#checkup_height').focus();
				 return false;
			}else if(checkup_weight==""){
				alert("체중을 입력해주세요.");
				 $('#checkup_weight').focus();
				 return false;
			}else if(checkup_leye==""){
				alert("시력을 입력해주세요.");
				 $('#checkup_leye').focus();
				 return false;
			}else if(checkup_reye==""){
				alert("시력을 입력해주세요.");
				 $('#checkup_reye').focus();
				 return false;
			}else if(checkup_lhear==""){
				alert("청력을 입력해주세요.");
				 $('#checkup_lhear').focus();
				 return false;
			}else if(checkup_rhear==""){
				alert("청력을 입력해주세요.");
				 $('#checkup_rhear').focus();
				 return false;
			}else if(checkup_maxblood==""){
				alert("이완기 혈압을 입력해주세요.");
				 $('#checkup_maxblood').focus();
				 return false;
			}else if(checkup_minblood==""){
				alert("수축기 혈압을 입력해주세요.");
				 $('#checkup_minblood').focus();
				 return false;
			}else if(checkup_hemo==""){
				alert("혈색소를 입력해주세요.");
				 $('#checkup_hemo').focus();
				 return false;
			}else if(checkup_beforesugar==""){
				alert("식전혈당을 입력해주세요.");
				 $('#checkup_beforesugar').focus();
				 return false;
			}else{
				return true;
			}
	}
		

	}
</script>

</head>
<body class="homepage is-preload">
	<%@ include file="/form/header.jsp" %>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/sub_04_03_bg.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/sub_04_01.css" />
		
	<!-- Intro -->
	<div id="page-wrapper">
	
				<section id="intro" class="wrapper style1">
					<div class="title"> 내 건강 정보 보기 <br> 건강검진입력 </div>
					<div class="container">
					<div>
					당신의 의료데이터를 입력하세요!<br>
				 	수기입력뿐만 아니라 xml 파일, 사진 파일을 업로드하여 쉽게 데이터를 입력할 수 있습니다.<br>
				 	또한 그래프를 통해 의료 데이터 추이를 쉽게 파악할 수 있습니다. 
					</div>
						<p class="style1">
						<div class="bg">
	<c:if test="${!empty loginDto }">
		
		<div id="imageInsertSuccess">${checkup_msg }</div><!-- 이미지 업로드 성공시 나타날 메세지 > 빨간 글씨나 아무튼 강조했으면 좋겠음 -->

		<c:if test="${checkup_type eq '1' }">
			<input type="radio" id="type1" name="checkup_type" value="1" checked="checked"> 기본
			<input type="radio" id="type2" name="checkup_type" value="2"> 개인
			<input type="radio" id="type3" name="checkup_type" value="3"> 정밀
		</c:if>
		
		<c:if test="${checkup_type eq '2' }">
			<input type="radio" id="type1" name="checkup_type" value="1"> 기본
			<input type="radio" id="type2" name="checkup_type" value="2" checked="checked"> 개인
			<input type="radio" id="type3" name="checkup_type" value="3"> 정밀
		</c:if>
		
		<c:if test="${checkup_type eq '3' }">
			<input type="radio" id="type1" name="checkup_type" value="1"> 기본
			<input type="radio" id="type2" name="checkup_type" value="2"> 개인
			<input type="radio" id="type3" name="checkup_type" value="3" checked="checked"> 정밀
		</c:if>
		
		<!-- 1번: 기본 검사 insert --><!-- 1번: 기본 검사 insert --><!-- 1번: 기본 검사 insert -->
		<fieldset id="formType_1">
			<form action="checkcontroller.do" method="post" onsubmit="return check()">
					<input type="hidden" name="command" value="type_1_insert">
					<input type="hidden" name="checkup_type" value="1">
					<input type="hidden" name="member_seq" value="${loginDto.member_seq }">
					<!-- 폼마다 member_seq 담아서 보내기 (필수) -->
					<table>
						<tr>
							<td colspan="2">
								<input type="button" value="이미지 업로드" onclick="location.href='logincontroller.do?command=imageUpload_type1'" id="btn">
								<input type="button" value="XML 업로드" onclick="location.href='logincontroller.do?command=xmlUpload_type1'" id="btn">
							</td>
						</tr>
						<tr>
							<th>측정일</th>
							<td>
							<select name="checkup_year">
									<%
										Calendar cal = Calendar.getInstance();
										int year = cal.get(Calendar.YEAR);
										for (int i = year; i >= year - 100; i--) {
									%>
									
									<option id="op" value="<%=i%>"><%=i%></option>
									<%
										}
									%>
							</select>년 
							
							<select name="checkup_month">
									<%
										for (int i = 1; i <= 12; i++) {
										if (i < 10) {
												String month = "0" + i;
									%>
									<option value="<%=month%>"><%=month%></option>
									<%
										} else {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
										}
									%>
							</select>월 
							
							<select name="checkup_day">
									<%
										for (int i = 1; i <= 31; i++) {
											if (i < 10) {
												String day = "0" + i;
									%>
									<option value="<%=day%>"><%=day%></option>
									<%
										} else {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
										}
									%>
							</select>일
							
							</td>
						</tr>
		
						<tr>
							<th>주민등록번호 <br> 앞자리</th>
							<td>
								<input type="text" name="checkup_regist" id="checkup_regist" value="${checkup_regist }" onKeyPress="return checkNum(event)" />
								
							</td>
						</tr>
		
						<tr>
							<th>신장</th>
							<td><input type="text" name="checkup_height" id="checkup_height" value="${checkup_height }"  onKeyPress="return checkNum(event)"/> cm 
							</td>
						</tr>
		
						<tr>
							<th>체중</th>
							<td><input type="text" name="checkup_weight" id="checkup_weight" value="${checkup_weight }"  onKeyPress="return checkNum(event)"/> kg
							</td>
						</tr>
		
						<tr>
							<th>시력좌</th>
							<td><input type="text" name="checkup_leye" id="checkup_leye" value="${checkup_leye }"  onKeyPress="return checkNum(event)"/>
							 </td>
						</tr>
		
						<tr>
							<th>시력우</th>
							<td><input type="text" name="checkup_reye" id="checkup_reye" value="${checkup_reye }"  onKeyPress="return checkNum(event)"/>
							</td>
						</tr>
		
						<tr>
							<th>청각좌</th>
							<td><input type="text" name="checkup_lhear" id="checkup_lhear" id="checkup_height" value="${checkup_lhear }"></td>
						</tr>
		
						<tr>
							<th>청각우</th>
							<td><input type="text" name="checkup_rhear" id="checkup_rhear" value="${checkup_rhear }"></td>
						</tr>
		
						<tr>
							<th>혈압최고</th>
							<td><input type="text" name="checkup_maxblood" id="checkup_maxblood" value="${checkup_maxblood }"  onKeyPress="return checkNum(event)"> /mmHg</td>
						</tr>
		
						<tr>
							<th>혈압최저</th>
							<td><input type="text" name="checkup_minblood" id="checkup_minblood" value="${checkup_minblood }"  onKeyPress="return checkNum(event)"> /mmHg</td>
						</tr>
		
						<tr>
							<th>요단백</th>
							<td>
								<c:choose>
									<c:when test="${checkup_kidney eq '양성' }">
										<input type="radio" name="checkup_kidney" value="양성" checked="checked"> 양성
										<input type="radio" name="checkup_kidney" value="음성"> 음성
									</c:when>
									<c:when test="${checkup_kidney eq '음성' }">
										<input type="radio" name="checkup_kidney" value="양성"> 양성
										<input type="radio" name="checkup_kidney" value="음성" checked="checked"> 음성
									</c:when>
									<c:otherwise>
										<input type="radio" name="checkup_kidney" value="양성"> 양성
										<input type="radio" name="checkup_kidney" value="음성"> 음성
									</c:otherwise>
								</c:choose>
								<br><br>
							</td>
						</tr>
		
						<tr>
							<th>혈색소</th>
							<td><input type="text" name="checkup_hemo" id="checkup_hemo" value="${checkup_hemo }"  onKeyPress="return checkNum(event)"/> g/dL</td>
						</tr>
						
						<tr>
							<th>식전혈당</th>
							<td><input type="text" name="checkup_beforesugar" id="checkup_beforesugar" value="${checkup_beforesugar }"  onKeyPress="return checkNum(event)" /> mg/dL</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<input type="submit" value="작성" id="btn1"/>
								<input type="button" value="취소" id="btn1"/>
							</td>
						</tr>

					</table>
				</form>
				
		</fieldset>
	
		<!--2번: 기본 검사 insert --><!--2번: 기본 검사 insert --><!--2번: 기본 검사 insert -->
		<fieldset id="formType_2">
			<form action="checkcontroller.do" method="post">
			<input type="hidden" name="checkup_type" value="2">
			<input type="hidden" name="command" value="type_2_insert">
			<input type="hidden" name="member_seq" value="${loginDto.member_seq }">
			<!-- 폼마다 member_seq 담아서 보내기 (필수) -->
			
			<table border="1">
						<tr>
							<td colspan="2">
								<input type="button" value="이미지 업로드" onclick="location.href='logincontroller.do?command=imageUpload_type2'" id="btn">
								<input type="button" value="XML 업로드" onclick="location.href='logincontroller.do?command=xmlUpload_type2'" id="btn">
							</td>
						</tr>
						<tr>
							<th>측정일</th>
							<td>
							
							<select name="checkup_year">
									<%
										for (int i = year; i >= year - 100; i--) {
									%>
									
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
							</select>년 
							
							<select name="checkup_month">
									<%
										for (int i = 1; i <= 12; i++) {
										if (i < 10) {
												String month = "0" + i;
									%>
									<option value="<%=month%>"><%=month%></option>
									<%
										} else {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
										}
									%>
							</select>월 
							
							<select name="checkup_day">
									<%
										for (int i = 1; i <= 31; i++) {
											if (i < 10) {
												String day = "0" + i;
									%>
									<option value="<%=day%>"><%=day%></option>
									<%
										} else {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
										}
									%>
							</select>일
							
							</td>
						</tr>
		
						<tr>
							<th>주민등록번호<br>앞자리</th>
							<td><input type="text" name="checkup_regist" value="${checkup_regist }"  onKeyPress="return checkNum(event)"/></td>
                            
						</tr>
		
						<tr>
							<th>신장</th>
							<td><input type="text" name="checkup_height" value="${checkup_height }"  onKeyPress="return checkNum(event)"/> cm</td>
                            </tr>

						<tr>
							<th>체중</th>
							<td><input type="text" name="checkup_weight" value="${checkup_weight }"  onKeyPress="return checkNum(event)"/> kg</td>
                        </tr>
		
						<tr>
							<th>혈압최고</th>
							<td><input type="text" name="checkup_maxblood" value="${checkup_maxblood }"  onKeyPress="return checkNum(event)"/> /mmHg</td>
						</tr>
		
						<tr>
							<th>혈압최저</th>
							<td><input type="text" name="checkup_minblood" value="${checkup_minblood }"  onKeyPress="return checkNum(event)"/> /mmHg</td>
						</tr>
		
						<tr>
							<th>요단백</th>
							<td>
								<c:choose>
									<c:when test="${checkup_kidney eq '양성' }">
										<input type="radio" name="checkup_kidney" value="양성" checked="checked"> 양성
										<input type="radio" name="checkup_kidney" value="음성"> 음성
									</c:when>
									<c:when test="${checkup_kidney eq '음성' }">
										<input type="radio" name="checkup_kidney" value="양성"> 양성
										<input type="radio" name="checkup_kidney" value="음성" checked="checked"> 음성
									</c:when>
									<c:otherwise>
										<input type="radio" name="checkup_kidney" value="양성"> 양성
										<input type="radio" name="checkup_kidney" value="음성"> 음성
									</c:otherwise>
								</c:choose>
								<br><br>
							</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<input type="submit" value="작성" id="btn1"/>
								<input type="button" value="취소" id="btn1"/>
							</td>
						</tr>

					</table>
					</form>

		</fieldset>
		
		<!-- 3번 정밀 검사 --><!-- 3번 정밀 검사 --><!-- 3번 정밀 검사 --><!-- 3번 정밀 검사 --><!-- 3번 정밀 검사 -->
		<fieldset id="formType_3">
			<form action="checkcontroller.do" method="post">
			<input type="hidden" name="checkup_type" value="3">
			<input type="hidden" name="command" value="type_3_insert">
			<input type="hidden" name="member_seq" value="${loginDto.member_seq }">
			<!-- 폼마다 member_seq 담아서 보내기 (필수) -->
			
			<table border="1">
						<tr>
							<td colspan="2">
								<input type="button" value="이미지 업로드" onclick="location.href='logincontroller.do?command=imageUpload_type3'" id="btn">
								<input type="button" value="XML 업로드" onclick="location.href='logincontroller.do?command=xmlUpload_type3'" id="btn">
							</td>
						</tr>
						<tr>
							<th>측정일</th>
							<td>
							
							<select name="checkup_year">
									<%
										for (int i = year; i >= year - 100; i--) {
									%>
									
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
							</select>년 
							
							<select name="checkup_month">
									<%
										for (int i = 1; i <= 12; i++) {
										if (i < 10) {
												String month = "0" + i;
									%>
									<option value="<%=month%>"><%=month%></option>
									<%
										} else {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
										}
									%>
							</select>월 
							
							<select name="checkup_day">
									<%
										for (int i = 1; i <= 31; i++) {
											if (i < 10) {
												String day = "0" + i;
									%>
									<option value="<%=day%>"><%=day%></option>
									<%
										} else {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
										}
									%>
							</select>일
							
							</td>
						</tr>
		
						<tr>
							<th>안저검사</th>
							<td><input type="text" name="checkup_fundo" value="${checkup_fundo }"  onKeyPress="return checkNum(event)"/></td>
						</tr>

						<tr>
							<th>식전혈당</th>
							<td><input type="text" name="checkup_beforesugar" value="${checkup_beforesugar }"  onKeyPress="return checkNum(event)"/> mg/dL</td>
						</tr>
				
						<tr>
							<th>식후혈당</th>
							<td><input type="text" name="checkup_aftersugar" value="${checkup_aftersugar }"  onKeyPress="return checkNum(event)"/> mg/dL</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<input type="submit" value="작성" id="btn1" />
								<input type="button" value="취소" id="btn1" />
							</td>
						</tr>

					</table>
					</form>
		</fieldset>

	</c:if>
	
	<c:if test="${empty loginDto }">
<script type="text/javascript">
	alert('로그인한 회원만 이용 가능한 메뉴입니다.');
	location.href='logincontroller.do?command=loginclick';
</script>
	</c:if>
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