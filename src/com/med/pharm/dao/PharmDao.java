package com.med.pharm.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.XML;

public class PharmDao {
	
public JSONObject getPharm(String sido, String sigungu, String qn) throws IOException {
		
		String addr = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire?serviceKey=";
		String serviceKey = "hVxhuHJPuBSJa3MLQyj8KR3znq2NdUL5L9UX%2BS7DEqaE93bQOiHVdfw1TdiwhnwGbs2PmQpFCHjX2ZjQhDVR0A%3D%3D";
		String parameter="";
//		serviceKey = URLEncoder.encode(serviceKey, "UTF-8");
	
		
		
		if(sigungu.equals("구군 선택") ) {
			
			qn = URLEncoder.encode(qn, "UTF-8");
			
			parameter = parameter + "&" + "QN=" + qn;
			parameter = parameter + "&" + "pageNo=1";
			parameter = parameter + "&" + "numOfRows=10";
			parameter = parameter + "&" + "?_type=json";
			
			addr = addr + serviceKey+parameter;
//			System.out.println(addr);
			
		} else {
			
			sido = URLEncoder.encode(sido, "UTF-8");
			sigungu = URLEncoder.encode(sigungu, "UTF-8");
			qn = URLEncoder.encode(qn, "UTF-8");
			
			parameter = parameter + "&" + "Q0=" + sido;
			parameter = parameter + "&" + "Q1=" + sigungu;
			parameter = parameter + "&" + "QN=" + qn;
			parameter = parameter + "&" + "pageNo=1";
			parameter = parameter + "&" + "numOfRows=10";
			parameter = parameter + "&" + "?_type=json";
		
			addr = addr + serviceKey+parameter;
//			System.out.println(addr);
			
		}
	
			
		
		
		URL url = new URL(addr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		System.out.println(url);
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-type", "application/json");
		
//		System.out.println("출력 결과 : " + con.getResponseCode());
		
		
		BufferedReader br = null; 
		
		if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		

		StringBuilder sb = new StringBuilder();
		String line = ""; 
		
		while((line= br.readLine())!= null){ 
			sb.append(line);
		}
		
		br.close();
		con.disconnect();
		
		
		JSONObject xmlJSONobj = new JSONObject();
		
        xmlJSONobj = XML.toJSONObject(sb.toString());
        
//        String test = xmlJSONobj.toString(4);
//        System.out.println(test);
        		
        return xmlJSONobj;
	
	}

}
