<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.med.check.dto.CheckDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%request.setCharacterEncoding("UTF-8");%>
    <%response.setContentType("text/html; charset=UTF-8");%>
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal MEDI</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

<!-- tooltip css-->
<style type="text/css">

[data-tooltip-text]:hover {
	position: relative;
}

[data-tooltip-text]:after {
	-webkit-transition: bottom .3s ease-in-out, opacity .3s ease-in-out;
	-moz-transition: bottom .3s ease-in-out, opacity .3s ease-in-out;
	transition: bottom .3s ease-in-out, opacity .3s ease-in-out;

	background-color: rgba(0, 0, 0, 0.8);

  -webkit-box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
	-moz-box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
	box-shadow: 0px 0px 3px 1px rgba(50, 50, 50, 0.4);
	
  -webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	
  color: #FFFFFF;
	font-size: 14px;
	margin-bottom: 10px;
	padding: 7px 12px;
	position: absolute;
	width: auto;
	min-width: 100px;
	max-width: 400px;
	word-wrap: break-word;

	z-index: 9999;

	opacity: 0;
	left: -9999px;
  top: 90%;
	
	content: attr(data-tooltip-text);
}

[data-tooltip-text]:hover:after {
	top: 130%;
	left: 0;
	opacity: 1;
}
</style>


<body class="homepage is-preload">

	<%@ include file="/form/header.jsp" %>

	
<link rel="stylesheet" href="assets/css/myhealth_bg.css" />
<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
<link rel="stylesheet" type="text/css" href="assets/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/myhealth.css" />

<div id="page-wrapper">
	

<!-- Intro -->
<section id="intro" class="wrapper style1">
<div class="title"> 내 건강 정보 보기 <br> 검진결과 보기 </div>
<div class="container">
<p class="style1">

<img src="images/myhealth.png" alt="" id="myhealth">

<c:if test="${!empty loginDto }">
	
<%
    List<CheckDto> list = (List<CheckDto>)request.getAttribute("list");
    List<CheckDto> list2 = (List<CheckDto>)request.getAttribute("list2");
    String member_id = (String)request.getAttribute("member_id");
%>
    <c:if test="${list2.size()>=1 }">

<table id="my">


<tr>

<td colspan="3">아이디 : <%=member_id %></td>
</tr>
<tr>
<td colspan="3"><%=list2.get(list2.size()-1).getCheckup_year() %>년 
<%=list2.get(list2.size()-1).getCheckup_month() %>월
<%=list2.get(list2.size()-1).getCheckup_day() %>일
 </td>
</tr>
<tr>


<td>신장 : </td>
<td><%=list2.get(list2.size()-1).getCheckup_height() %></td>
</tr>
<tr>

<td>체중 : </td>
<td><%=list2.get(list2.size()-1).getCheckup_weight() %></td>
</tr>
<tr>

<td data-tooltip-text="시력검사란? 시력 및 초점 능력 등을 검사"> 시력(좌) : <%=list2.get(list2.size()-1).getCheckup_leye() %></td>
<td data-tooltip-text="시력검사란? 시력 및 초점 능력 등을 검사">시력(우) : <%=list2.get(list2.size()-1).getCheckup_reye() %></td>
</tr>
<tr>

<td data-tooltip-text="청력검사란? 소리를 듣는 능력을 평가하는 검사로, 청력이 손실되었는지를 판별하는 검사">청력(좌) : <%=list2.get(list2.size()-1).getCheckup_lhear() %></td>
<td data-tooltip-text="청력검사란? 소리를 듣는 능력을 평가하는 검사로, 청력이 손실되었는지를 판별하는 검사">청력(우) : <%=list2.get(list2.size()-1).getCheckup_rhear() %></td>
</tr>
<tr>

<td data-tooltip-text="요단백이란? 단백질이 신장에서 걸러지지 않고 소변을 통해 체외로 배출되는 것(소변 속에 단백질 검출 반응 여부 확인), 음성(0)과 양성(1)으로 표기">요단백 : </td>
<td><%=list2.get(list2.size()-1).getCheckup_kidney() %></td>
</tr>
<tr>

<td data-tooltip-text="혈압이란? 혈관을 따라 흐르는 혈액이 혈관의 벽에 주는 압력, 정상수치: 이완기 80mmHg 미만, 수축기 120mmHg 미만/이완기 혈압이 95mmHg 이상, 수축기 145mmHg 이상일 경우 2차 진단 필요">수축기 혈압 : <%=list2.get(list2.size()-1).getCheckup_maxblood() %> </td>
<td data-tooltip-text="혈압이란? 혈관을 따라 흐르는 혈액이 혈관의 벽에 주는 압력, 정상수치: 이완기 80mmHg 미만, 수축기 120mmHg 미만/이완기 혈압이 95mmHg 이상, 수축기 145mmHg 이상일 경우 2차 진단 필요">이완기 혈압 :  <%=list2.get(list2.size()-1).getCheckup_minblood() %></td>
</tr>
<tr>

<td data-tooltip-text="혈색소란? 적혈구의 주요 성분으로 산소와 이산화탄소를 운반하는 역할을 수행(혈액의 산소운반 능력 판단), 혈색소 수치가 높으면 혈당과다/낮으면 빈혈·관절염·백혈병 등을 의심, 정상수치: 남성 100mL당 13~16.6g/dL, 여성 12~15.5g/dL">혈색소 : </td>
<td> <%=list2.get(list2.size()-1).getCheckup_hemo() %></td>
</tr>
<tr>

<td data-tooltip-text="혈당이란? 혈액 속에 함유되어 있는 포도당의 농도, 정상수치: 8시간 금식한 후 70~120mg/dL, 보통 126mg/dL이 넘으면 당뇨병으로 진단 ">식전 혈당: </td>
<td><%=list2.get(list2.size()-1).getCheckup_beforesugar() %></td>
</tr>
</table>
	
	
	
    
  <!-- 비만 체중을 키의 제곱으로 나눈 체질량지수(BMI)를 기준으로 한다. 18.5~24.9까지가 정상이며 26부터는 과체중, 30부터는 고도비만  -->

 
  
 <!-- Sugar Chart -->
 <%
 String [] date = new String[list.size()];
 String [] aftersugar = new String[list.size()];
 String [] beforesugar = new String[list.size()];
 for(int i=0; i<list.size();i++){
	 date [i] = list.get(i).getCheckup_year()+list.get(i).getCheckup_month()+list.get(i).getCheckup_day();
	 aftersugar [i] = list.get(i).getCheckup_aftersugar(); 
	 beforesugar [i] = list.get(i).getCheckup_beforesugar();  
 }
 System.out.println(Arrays.toString(date)+"날짜 나오니");
 System.out.println(Arrays.toString(aftersugar)+"값나오니");
 %>
 
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<div style="height:60px;"></div>
<div id="container1" style="width: 500px; height: 375px; margin: 0 auto;"></div>


<script type="text/javascript">
$(function() {
	  $('#container1').highcharts({
	    chart: {
	      alignTicks: false
	    },
	    title: {
	      text: 'Blood sugar Chart',
	      x: -20 //center
	    },
	    subtitle: {
	        text: '<a href="" target="_blank" >(정상수치: 8시간 금식한 후 70~120mg/dL)</a>'
	    },
	    xAxis: {
	      categories: <%=Arrays.toString(date)%>
	    },
	    yAxis: [{ // Primary yAxis
	      min: 80,
	      max: 120,
	      gridLineWidth: 0,
	      tickInterval: 10,
	      labels: {
	        format: '{value:.1f}',
	        style: {
	          color: Highcharts.getOptions().colors[1]
	        }
	      },
	      title: {
	        text: '혈당수치(mmHg)',
	        style: {
	          color: Highcharts.getOptions().colors[1]
	        }
	      }
	    }, { // Secondary yAxis
	      min: 80,
	      max: 120,
	      gridLineWidth: 0,
	      tickInterval: 10.0,
	      title: {
	        text: "",
	        style: {
	          color: Highcharts.getOptions().colors[0]
	        }
	      },
	      labels: {
	        format: '{value:.1f}',
	        style: {
	          color: Highcharts.getOptions().colors[0]
	        }
	      },
	      opposite: true
	    }],
	    legend: {
	      layout: 'horizontal',
	      verticalAlign: 'top',
	    },
	    series: [{
	      name: '식후혈당',
	      yAxis: 1,
	      data: <%=Arrays.toString(aftersugar)%>
	    }, {
	      name: '식전혈당',
	      yAxis: 0,
	      data: <%=Arrays.toString(beforesugar)%>
	    }]
	  });
	});
	

</script>


<!-- Hemo chart -->

<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<div style="height:60px;"></div>
    <div id="container2" style="width: 500px; height: 375px; margin: 0 auto"></div>

<%
ArrayList<String> hemos = new ArrayList<String>();
String [] hemo = new String[list2.size()];
for(int i=0; i<list2.size();i++){
	 
	String day = list2.get(i).getCheckup_year()+list2.get(i).getCheckup_month()+list2.get(i).getCheckup_day();
	 
	hemo[i] = "["+"'"+day+"'"+","+list2.get(i).getCheckup_hemo()+"]";
	hemos.add(hemo[i]);
}

%>
<script type="text/javascript">
Highcharts.chart('container2', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Hemoglobin Chart'
    },
    subtitle: {
        text: '<a href="" target="_blank">(정상수치: 남성 100mL당 13~16.6g/dL, 여성 12~15.5g/dL)</a>'
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: '혈색소 수치(g/dL)'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: '<b>{point.y:.1f} g/dL</b>'
    },
    series: [{
        name: 'Population',
        data: 
        	<%=hemos%>
        ,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}',
            y: 10, 
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
});
</script>

<!-- blood chart -->
<div style="height:60px;"></div>
    <div id="container3" style="width: 500px; height: 375px; margin: 0 auto"></div>
<%
String [] days = new String[list.size()];
String [] max = new String[list.size()];
String [] min = new String[list.size()];
for(int i=0; i<list.size();i++){
	String day = list.get(i).getCheckup_year()+list.get(i).getCheckup_month()+list.get(i).getCheckup_day();
	 
	days[i] = day;
	max[i] = list.get(i).getCheckup_maxblood();
    min[i] = list.get(i).getCheckup_minblood();

}

%>
<script type="text/javascript">
Highcharts.chart('container3', {
    chart: {
        type: 'areaspline'
    },
    title: {
        text: 'Blood pressure Chart'
    },
    subtitle: {
        text: '<a href="" target="_blank">(정상수치: 이완기 80mmHg 미만, 수축기 120mmHg 미만)</a>'
    },
    legend: {
        layout: 'vertical',
        align: 'left',
        verticalAlign: 'top',
        x: 150,
        y: 100,
        floating: true,
        borderWidth: 1,
        backgroundColor:
            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF'
    },
    xAxis: {
        categories: <%=Arrays.toString(days)%>,
        plotBands: [{ // visualize the weekend
            from: 4.5,
            to: 6.5,
            color: 'rgba(68, 170, 213, .2)'
        }]
    },
    yAxis: {
    	min: 70,
	    max: 150,
        title: {
            text: '혈압 수치(mmHg)'
        }
    },
    tooltip: {
        shared: true,
        valueSuffix: ' mmHg'
    },
    credits: {
        enabled: false
    },
    plotOptions: {
        areaspline: {
            fillOpacity: 0.5
        }
    },
    series: [{
        name: 'Max',
        data: <%=Arrays.toString(max)%>
    }, {
        name: 'Min',
        data: <%=Arrays.toString(min)%>
    }]
});
</script>
	</c:if>
	<c:if test="${list2.size()<1 }">
	  <script type="text/javascript">
      alert("데이터를 입력한 회원만 이용 가능합니다.");
      location.href='checkcontroller.do?command=insert_type1';
	  </script>
	</c:if>
	 </c:if>
	<c:if test="${empty loginDto }">
		<script type="text/javascript">
		alert("로그인한 회원만 이용 가능합니다.");
        location.href='logincontroller.do?command=loginclick';
	  </script>
		
	</c:if>
	
	
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



</div>
<!-- Scripts -->


</body>
</html>