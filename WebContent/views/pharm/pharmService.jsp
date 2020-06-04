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
</head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
<script> 

	var curLevel = 0; 
	
	$(document).ready(function(){
		getLocation(0); 
		}
	); 
	
	function myPOST(v) { 
		var arr = v; 
		if(curLevel == 0) { 
			for(var i=0; i<arr.length; i++) {
				$('#sido').append('<option value="' + arr[i].sido + '">' + arr[i].sido + '</option>'); 
				} 
			} else if(curLevel == 1) { 
				for(var i=0; i<arr.length; i++) { 
					$('#sigungu').append('<option value="' + arr[i].sigungu + '">' + arr[i].sigungu + '</option>'); } 
				} else if(curLevel == 2) { 
					for(var i=0; i<arr.length; i++) { 
						$('#dong').append('<option value="' + arr[i].dong + '">' + arr[i].dong + '</option>'); } 
					} else { 
						
					} 
		} 
	
	function getLocation(v) { 
		curLevel = v; 
		if(v == 0) { 
			$('#sido').empty().append('<option value="">시도 선택</option>'); 
			} else if(v == 1) { 
				$('#sigungu').empty().append('<option value="">구군 선택</option>'); 
				$('#dong').empty().append('<option value="">읍면동 선택</option>'); 
				} else if(v == 2) { 
					$('#dong').empty().append('<option value="">읍면동 선택</option>'); 
					} else { 
						
					} 
		
		var sido = $('#sido').val(); 
		var sigungu = $('#sigungu').val(); 
		var request = $.ajax({ 
			type: 'POST', 
			data: {
				mdiv:'json',
				sido:sido,
				sigungu:sigungu}, 
			url: 'https://www.ivps.kr/zipcode/zipcode.php', 
			dataType: "jsonp", 
			jsonpCallback: "myPOST", 
			success:function(result){ 
				console.log('ret : ' + result); 
				}
			, error:function(xhr, status, error){ 
				console.log('status : ' + xhr.status); 
				} 
			}); 
		} 

</script>
	

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">





	function searchPharm() {
	
		var sido = $("#sido option:selected").val();
		var sigungu = $("#sigungu option:selected").val();
		var qn = $("#qn").val();

	
	$.ajax({
		type: 'POST',
		data: {
			mdiv:'json',
			sido:sido,
			sigungu:sigungu,
			qn:qn
			},
		url: 'PharmService.do?command=PharmSearch',	
		dataType: "json",
		anync : false,
		success: function(data) {
			if (data == "" || data == null) {
				alert("검색 결과가 없습니다");
			} else {
				
				$("#tbodyResult").empty();
				$.each(data, function(key, val) {
					$("#tbodyResult").show();
					$("#tbodyResult").append(
							
							"<tr class='mapping' id='" + val.wgs84Lat + "' name='"+val.wgs84Lon+"'>" +
								"<td>" + "<img src='images/mapicon.png' style='width: 10px;'>&nbsp;&nbsp;" + val.dutyName + "</td>"+
								"<td>" + val.dutyTel1 + "</td>"+
								"<td>" + val.dutyAddr + "</td>"+ 
							"</tr>"
							

					);
					
				
				});
				
				
				
			
			
				
				var isChecked = $("#tbodyResult .mapping").length;
				
				$("#tbodyResult .mapping").click(function() {

					var lat = $(this).attr('id');
					var lon = $(this).attr('name');
					
					var container = document.getElementById('tbodyMap');
		    		var options = {
		    			center: new kakao.maps.LatLng(lat, lon),
		    			level: 2
		    		};
			
			
		    		var map = new kakao.maps.Map(container, options);
		    		
		    		// 마커가 표시될 위치입니다 
		    		var markerPosition  = new kakao.maps.LatLng(lat, lon); 

		    		// 마커를 생성합니다
		    		var marker = new kakao.maps.Marker({
		    		    position: markerPosition
		    		});

		    		// 마커가 지도 위에 표시되도록 설정합니다
		    		marker.setMap(map);

				})
				
				
				
			} 
			
		
			

		}, error: function() {
			alert('에러');
		}
		
	}); 
	
	}	
        
</script>



<body class="homepage is-preload">


	
	<%@ include file="/form/header.jsp" %>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/search_bg_p.css" />
		<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
		<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/css/search_p.css" />
		
		<div id="page-wrapper">
	<!-- Intro -->
				<section id="intro" class="wrapper style1">
					<div class="title"> 약국 찾기</div>
					<div class="container">
					<div>
					가까운 병원, 약국을 찾아보세요!<br>
					우리 주변 병원과 약국 정보를 클릭하여 위치 정보도 확인할 수 있습니다.
					</div>
						<p class="style1">
	
	<form action="PharmService.do" method="post" onsubmit="return false">
	<table border="1">
		<tbody id="tbodySearch">
		<tr>
			<td colspan="3">
				<select name="sido" id="sido" onchange="getLocation(1);" class="sele"> 
					<option>시도 선택</option> 
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select name="sigungu" id="sigungu" onchange="getLocation(2);" class="sele"> 				
					<option>구군 선택</option> 
				</select> 
		 	</td>
		 </tr>	
		 		
			
			
			<tr>
			
				<td colspan="4">  
					<input type="text" id="qn" placeholder="약국이름으로 검색">
				
		 			<input type="button" value="약국확인하기" onclick="searchPharm();">
		 		</td>
		 	</tr>	
		 
		 
	
		 
		</tbody>
	
			
		<tbody id="tbodyResult"></tbody>
	</table>		 
	</form> 
	
		<div id="tbodyMap" style="width:1270px; height:600px;"></div>
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
 
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=64caf5ec076348c4bd7e48f89749240f"></script>	
<script>

	
/* function map() {
		
			var lat = $(".mapping").attr('id');
			alert(lat);
			var lon = $(this).val();

		
    		var container = document.getElementById('tbodyMap');
    		var options = {
    			center: new kakao.maps.LatLng(lat, lon),
    			level: 3
    		};
	
	
    		var map = new kakao.maps.Map(container, options);
    		
    		// 마커가 표시될 위치입니다 
    		var markerPosition  = new kakao.maps.LatLng(lat, lon); 

    		// 마커를 생성합니다
    		var marker = new kakao.maps.Marker({
    		    position: markerPosition
    		});

    		// 마커가 지도 위에 표시되도록 설정합니다
    		marker.setMap(map);
    		

 
} */

</script>

</body>
</html>