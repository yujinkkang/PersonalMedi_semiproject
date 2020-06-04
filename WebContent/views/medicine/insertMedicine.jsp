<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body class="homepage is-preload">
	<%@ include file="/form/header.jsp" %>
		<link rel="stylesheet" href="assets/css/sub_02_01_bg.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/sub_02_01.css" />
	<div id="page-wrapper">
		<section id="intro" class="wrapper style1">
					<div class="title"> 내 약 정보 보기 <br> 약 검색 / 등록 / 안정성 확인 </div>
					<div class="container">
					<div>
					처방받은 약을 검색하고 안정성을 확인하세요!<br>
 					처방전을 기록하고 확인할 수 있습니다. 처방받은 알약을 검색해보고 해당 약의 안전 사용성 정보를 확인해보세요.
					</div>
						<p class="style1">

							
							  <div class="bg">
							  
		<form action="medcontroller.do" method="post" onsubmit="return false">
	<table border="1" id="table1">
		<tbody id="tbodySearch">
			<tr>
			<td colspan="2">
				<input type="text" name="med_name" placeholder="약 이름을 입력해 주세요">
			</td>
			<td class="searchbtn">
				<input type="button" value="검색하기" onclick="searchMedicine();">
			</td>
		</tr>
		</tbody>
		<tbody id="tbodyResult"></tbody>
	</table>
	</form><br>
	<div>
	<c:if test="${empty list }">
		등록된 약품이 없습니다
	</c:if>
	</div>
		<c:if test="${!empty list }">
			<div id="druglist">
				<c:forEach items="${list }" var="list">
				
					<div id="${list.mydrug_seq }" class=align>
						<table border="1" id="align">
							<tr>
								<td>
									<input type="checkbox" class="durcheck" value="${list.mydrug_name }" id="${list.drug_code }">
								</td>
							</tr>
							<tr>
								<th>
									${list.mydrug_name }
								</th>
							</tr>
							<tr>
								<td>
									<img src="${list.mydrug_image }">
								</td>
							</tr>
							<tr>
								<td>등록 날짜: ${list.mydrug_date }</td>
							</tr>
							<tr>
								<td>
									${list.mydrug_about }
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">
									<input type="button" id="btn1" value="삭제하기" onclick="location.href='medcontroller.do?command=deleteMedicine&seq=${list.mydrug_seq}'">
								</td>
							</tr>

						</table>
					</div>
				</c:forEach>
				
			</div>
			
			<div id="durResult">
				<table border="1" id="tdur">
					<tbody id="tbodyDUR">
						<tr>
							<th>약품 이름</th>
							<th>약품 설명</th>
							<th>DUR 타입</th>
						</tr>
					</tbody>
				</table>
			</div>
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

<script type="text/javascript">

	$(function() {
		$("#tbodyResult").hide();
		$("#durResult").hide();
		
		$(".durcheck").click(function() {
			var item_name = $(this).val();
			
			if ($(this).is(":checked")) {
				
				$.ajax({
					type: 'POST',
					url: 'medcontroller.do?command=isDur',
					data: {item_name:item_name},
					dataType: "json",
					success: function(durdata) {
						if (durdata == "" || durdata == null) {
							alert('등록된 DUR 정보가 없습니다.');
						
						} else {
							$("#durResult").show();
							
							$("#tbodyDUR").append(
								"<tr class='durdata_item_name' id=" + durdata.ITEM_SEQ + ">" +
								"<td>" + durdata.ITEM_NAME + "</td>" + 
								"<td>" + durdata.EFFECT_NAME + "</td>" + 
								"<td>" + durdata.TYPE_NAME + "</td>" +
								"</tr>"
							);
	
						}
					}, error: function() {
						alert('에러');
					}
				});

			} else {
				var itemCheck = $(this).attr('id');
				var isChecked = $(".durdata_item_name").length;
				
				for (var i = 0; i < isChecked ; i++) {
					var res = $(".durdata_item_name").eq(i).attr("id");
					
					if(res == itemCheck) {
						
						if(isChecked == 1) {
							$('#tbodyDUR > #' + res).remove();
							$("#durResult").hide();
						} else {
							$('#tbodyDUR > #' + res).remove();
						}						
					}
					
				}

			}
			
		})
		
	});
	
	function searchMedicine() {
		var med_name = document.getElementsByName("med_name")[0].value;
		$.ajax({
			type: 'POST',
			url: 'medcontroller.do?command=medicineSearch',
			data: {item_name:med_name},
			dataType: "json",
			success: function(data) {
				if (data == "" || data == null) {
					alert("검색 결과가 없습니다");
				} else {
					$("#tbodyResult").empty();
					$.each(data, function(key, val) {
						$("#tbodyResult").show();
						$("#tbodyResult").append(
								"<tr>"+
									"<td>"+
									"<input type='hidden' name='command' value='medicineUpdate'>"+
									"<input type='radio' name='item_seq' value='"+ val.ITEM_SEQ +"'>"+
									"</td>"+
									"<td>" + val.ITEM_NAME + "</td>"+
									"<td>" + val.CLASS_NAME + "</td>"+
								"</tr>"
							);
					});
					$("#tbodyResult").append("<tr><td colspan='3'><input type='submit' value='등록하기' onclick='form.submit()'></td></tr>");
				}

			}, error: function() {
				alert('에러');
			}
		})
	}
	
</script>
		<!-- Scripts -->
			
</body>

</html>