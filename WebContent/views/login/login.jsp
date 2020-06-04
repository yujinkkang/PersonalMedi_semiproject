<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<script type="text/javascript" src= "https://code.jquery.com/jquery-3.4.1.min.js"></script>

<!-- 카카오 로그인 --><!-- 카카오 로그인 --><!-- 카카오 로그인 --><!-- 카카오 로그인 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<!-- 카카오 로그인 --><!-- 카카오 로그인 --><!-- 카카오 로그인 --><!-- 카카오 로그인 -->
<!-- 네이버 로그인 -->
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<!-- 네이버 로그인 -->
<script type="text/javascript">
function registMember() {
	location.href="logincontroller.do?command=registMember";
}
</script>
</head>
<body>
<%@ include file="/form/header.jsp" %>

<link rel="stylesheet" href="assets/css/login_bg.css" />
<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/login.css" />
<body class="homepage is-preload">
<div id="page-wrapper">
	
<section id="intro" class="wrapper style1">
<div class="title"> LOG IN <br> 로그인 해주세요 ! </div>
<div class="container">
<p class="style1">

	<form action="logincontroller.do" method="post" id="formbg">
		<input type="hidden" name="command" value="login">
		<table style="width: 100%;">
			<col width="100"/>
			<col width="100"/>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw"></td>
			</tr>		
			<tr>
				<td colspan="2" align="center">
				    <input type="submit" value="&nbsp;로그인&nbsp;" id="btn1"><br><br>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				    <!-- 구글 로그인<br> -->
				    <a id="kakao-login-btn"></a>
					<a href="http://developers.kakao.com/logout"></a>
					<script type='text/javascript'>
					  //<![CDATA[
					    // 사용할 앱의 JavaScript 키를 설정해 주세요.
					    Kakao.init('154da91b1676d4f0b5c67cacc565cd84');
					    // 카카오 로그인 버튼을 생성합니다.
						Kakao.Auth.createLoginButton({
					    container: '#kakao-login-btn',
					    success: function(authObj) {
					          
					    Kakao.API.request({
					    url: '/v2/user/me',
					    success: function(res) {
    
					    var email = res.kakao_account.email;
					    location.href="logincontroller.do?command=snsCheck&member_email="+email+"&sns=k";
					              
					            },
					            fail: function(error) {
					              alert(JSON.stringify(error));
					            }
					          });
					       
					      },
					      fail: function(err) {
					         alert(JSON.stringify(err));
					      }
					    });
					  //]]>
					</script>
					<div id="naver_id_login"> 네이버 로그인</div><br>
					 <!-- 네이버 로그인2-->
    <script type="text/javascript">
  	  var naver_id_login = new naver_id_login("nf8GvwA3ulk1Y2HXWunM", "http://localhost:8090/semi_200310/views/login/naverLogin.jsp");
  	  var state = naver_id_login.getUniqState();
  	  naver_id_login.setButton("white", 2,40);
  	  naver_id_login.setDomain("http://localhost:8090/semi_200310/views/login/login.jsp");
  	  naver_id_login.setState(state);
  	  //naver_id_login.setPopup();
  	  naver_id_login.init_naver_id_login();
  	
    </script>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				    <input type="button" value="회원가입" id="btn1" onclick="registMember();"><br>
				    <!-- ID/PW 찾기 -->
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