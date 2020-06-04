<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
	$(function() {
		$('#emailHide').hide();
		$('#emailSubmit').hide();
		
		$('#pw2').blur(function(){
			if($('#pw1').val() != $('#pw2').val()){
					if($('#pw2').val()!=''){
						alert("비밀번호가 일치하지 않습니다.");
						$('#pw2').val('');
						$('#pw2').focus();
			       }
			    }
			});
		
		$('#member_email').blur(function() {
			var member_email = document.getElementsByName("member_email")[0].value;
			$.ajax({
				type: 'POST',
				url: 'logincontroller.do?command=emailCheck',
				data: {member_email:member_email},
				success: function(result) {
					if(result == 1) {
						$('#checkMessageEmail').html('사용할 수 없는 이메일입니다').css("color","yellow");
						document.getElementsByName("member_email")[0].focus();
						$("#reg_submit").attr("disabled", true);
						
					} else {
						
						if (member_email == "") {
							$('#checkMessageEmail').html('이메일을 입력해 주세요').css("color","yellow");
							$("#reg_submit").attr("disabled", true);
							
						} else {
							$('#checkMessageEmail').html('사용할 수 있는 이메일입니다.<br>이메일 인증을 진행해 주세요.').css("color","white");
							$('#emailHide').show();
							$('#emailSubmit').show();
							$("#reg_submit").attr("disabled", true);
						}
					}
				}
			});
		});
		
	});

	function idChkConfirm(){
		var chk = document.getElementsByName("member_id")[0].title;
		if (chk=="n") {
			alert("ID 중복 체크를 먼저 해주세요");
			document.getElementsByName("member_id")[0].focus();
		}
	}
	
	function idCheck() {
		var member_id = document.getElementsByName("member_id")[0].value;
		$.ajax({
			type: 'POST',
			url: 'logincontroller.do?command=idCheck',
			data: {member_id:member_id},
			success: function(result) {
				if(result == 1) {
					$('#checkMessage').html('사용할 수 없는 아이디입니다').css("color","yellow");
					document.getElementsByName("member_id")[0].focus();
					$("#reg_submit").attr("disabled", true);
					
				} else {
					if (member_id == "") {
						$('#checkMessage').html('아이디를 입력해 주세요').css("color","yellow");
						$("#reg_submit").attr("disabled", true);
						
					} else {
						$('#checkMessage').html('사용할 수 있는 아이디입니다').css("color","white");
						document.getElementsByName("member_id")[0].title='y';
						$("#reg_submit").attr("disabled", false);
					}
				}
			}
		});
	}
	
	function emailSubmit() {
		var email = document.getElementById("member_email").value;
		$.ajax({
			type: 'POST',
			url: 'send?command=emailsubmit',
			data: {member_email:email},
			success: function(data) {
				$('#checkMessageEmail').html('인증번호를 입력해 주세요').css("color","white");
			}, error: function() {
				$('#checkMessageEmail').html('에러!').css("color","yellow");
			}
			
		});
		return false;
	}

	function emailSubmitCheck() {
		var submitcode = document.getElementById("emailSubmitCode").value;
		$.ajax({
			type: 'POST',
			url: 'send?command=emailsubmitcheck',
			data: {inputcode:submitcode},
			success: function(result) {
				if (result == 0) {
					$('#emailResult').html('이메일 인증에 실패하였습니다.').css("color","yellow");
					$("#reg_submit").attr("disabled", true);
				} else {
					if (submitcode == "") {
						$('#emailResult').html('인증 코드를 입력해 주세요.').css("color","yellow");
					} else {
						$('#emailResult').html('이메일 인증에 성공하였습니다.').css("color","white");
						$("#reg_submit").attr("disabled", false);
					}
				}
			}, error: function() {
				alert('에러');
			}
			
		});
	}
	
	function jusoPopup(){
		var pop = null;
		pop = window.open("./views/login/jusoPopup.jsp","도로명주소찾기",
				"width=570, height=420, scrollbars=yes, resizable=yes"); 
	}
	
	function jusoCallBack(roadFullAddr){
		document.getElementById("member_addr").value=roadFullAddr;
	}
	
	function cancelRegist() {
		history.go(-1);
	}
	
</script>
</head>
<body class="homepage is-preload">
<div id="page-wrapper">
	
<%@ include file="/form/header.jsp" %>

<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/member_bg.css" />
<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/member.css" />

<!-- Intro -->
<section id="intro" class="wrapper style1">
<div class="title"> 회원가입</div>
<div class="container">
<p class="style1">

	<form action="logincontroller.do" method="post" id="formbg">
	<input type="hidden" name="command" value="insertMember"/>
	<input type="hidden" name="member_sns" value="${member_sns }">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="member_id" title="n" required>
					<input type="button" value="중복체크" onclick="idCheck();" class="btn1">
					<div id="checkMessage"></div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="member_pw" id="pw1" onclick="idChkConfirm();" required></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pw_chk" id="pw2" onclick="idChkConfirm();" required></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="member_name" onclick="idChkConfirm();" required></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					<select name="member_birth_yy">
<%
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					for(int i = year; i >= year-100 ; i--) {
%>
					<option value="<%=i%>"><%=i %></option>
<%						
					}
%>
					</select>년
					
					<select name="member_birth_mm">
<%
					for(int i = 1; i <= 12 ; i++) {
						if (i < 10) {
							String month = "0"+i;
%>
							<option value="<%=month%>"><%=month %></option>
<%
						} else {
%>
							<option value="<%=i%>"><%=i %></option>
<%
						}
					}
%>
					</select>월
					
					<select name="member_birth_dd">
<%
					for(int i = 1; i <= 31 ; i++) {
						if (i < 10) {
							String day = "0"+i;
%>
							<option value="<%=day%>"><%=day %></option>
<%
						} else {
%>
							<option value="<%=i%>"><%=i %></option>
<%
						}
					}
%>
					</select>일
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="member_gender" value="M" checked="checked"> 남
					<input type="radio" name="member_gender" value="F"> 여
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>			
				<input type="text" name="member_email" id="member_email" size="18" value="${member_email }" required>
				<input type="button" value="인증번호발송" id="emailHide" onclick="emailSubmit();" class="btn1">
				<div id="checkMessageEmail"></div>
				<div id="emailSubmit">
					<input type="text" id="emailSubmitCode" size="6" required>
					<input type="button" value="확인" id="emailSubmitButton" onclick="emailSubmitCheck();" class="btn0">
					<div id="emailResult"></div>
				</div>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="member_addr" id="member_addr" onclick="idChkConfirm();" required>
					<input type="button" value="주소찾기" onclick="jusoPopup();" id="btn1">
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td class="sel">
					<select name="member_phone1" id="member_phone1">
						<option value="010" selected>010</option>
						<option value="011">011</option>
						<option value="016">016</option> 
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					-
					<input type="text" name="member_phone2" id="member_phone2" maxlength="4" size="6" onclick="idChkConfirm();" required>
					-
					<input type="text" name="member_phone3" id="member_phone3" maxlength="4" size="6" onclick="idChkConfirm();" required>
					<div id="checkMessagePhone"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" id="reg_submit" value="가입하기" class="btn0">
					<input type="button" value="취소" onclick="cancelRegist();" id="btn1">
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