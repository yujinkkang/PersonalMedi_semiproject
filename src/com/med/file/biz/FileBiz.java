package com.med.file.biz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class FileBiz {
	
	public Map<String, String> getXML(String filePath) throws IOException{
		
		URL url = new URL(filePath);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");								//GET 방식으로 넘기는 거
		con.setRequestProperty("Content-type", "application/json");
		
		System.out.println("출력 결과 : " + con.getResponseCode());
		
		BufferedReader rd = null;
		if (con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		String line = "";
		
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		con.disconnect();
		// System.out.println(sb.toString());
		
		JSONObject xmlJSONobj = new JSONObject();
		xmlJSONobj = XML.toJSONObject(sb.toString());
		
		//신장, 체중, 시력, 청력
		JSONArray vital = xmlJSONobj.getJSONObject("ccr:ContinuityOfCareRecord")
										 .getJSONObject("ccr:Body")
										 .getJSONObject("ccr:VitalSigns")
										 .getJSONArray("ccr:Result");
		
		//혈압, 요검사, 공복혈당, 혈색소
		JSONArray detail = xmlJSONobj.getJSONObject("ccr:ContinuityOfCareRecord")
									 .getJSONObject("ccr:Body")
									 .getJSONObject("ccr:Results")
									 .getJSONArray("ccr:Result");
		
		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < vital.length() ; i++) {
			JSONObject vitalSigns = vital.getJSONObject(i);
			String res = vitalSigns.getJSONObject("ccr:Description").getString("ccr:Text");
			
			//map.containsKey("A") >> map에 "A"라는 key가 있는지 확인
			//여러 개의 건강검진 결과 값이 넘어오므로 가장 최신(맨 위)의 것만 값을 가져오기 위해 사용
			
			if (res.equals("신장")) {					//checkup_height
				String value = vitalSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getInt("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_HEIGHT"))) {		//key가 없을 경우만 put, 있으면 넘어감
					map.put("CHECKUP_HEIGHT", value);
				}
				
			} else if (res.equals("체중")) {			//checkup_weight
				String value = vitalSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getInt("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_WEIGHT"))) {
					map.put("CHECKUP_WEIGHT", value);
				}
				
			} else if (res.equals("시력(좌)")) {		//checkup_leye
				String value = vitalSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getDouble("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_LEYE"))) {
					map.put("CHECKUP_LEYE", value);
				}
				
			} else if (res.equals("시력(우)")) {		//checkup_reye
				String value = vitalSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getDouble("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_REYE"))) {
					map.put("CHECKUP_REYE", value);
				}
				
			} else if (res.equals("청력(좌/우)")) {		//checkup_lhear, checkup_rhear
				String value = vitalSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getString("ccr:Value");
				String[] hear = value.split("/");
				
				if (!(map.containsKey("CHECKUP_LHEAR"))) {
					map.put("CHECKUP_LHEAR", hear[0]);
				}
				
				if (!(map.containsKey("CHECKUP_RHEAR"))) {
					map.put("CHECKUP_RHEAR", hear[1]);
				}
				
			}			
		}
		
		for (int i = 0; i < detail.length() ; i++) {
			JSONObject detailSigns = detail.getJSONObject(i);
			String res = detailSigns.getJSONObject("ccr:Type").getString("ccr:Text");

			if (res.equals("혈압(수축기)")) {		//최고 혈압
				String value = detailSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getInt("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_MAXBLOOD"))) {
					map.put("CHECKUP_MAXBLOOD", value);
				}
				
			} else if (res.equals("혈압(이완기)")) {
				String value = detailSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getString("ccr:Value");
				if (!(map.containsKey("CHECKUP_MINBLOOD"))) {
					map.put("CHECKUP_MINBLOOD", value);
				}
				
			} else if (res.equals("요검사")) {
				String value = detailSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getString("ccr:Value");
				if (!(map.containsKey("CHECKUP_KIDNEY"))) {
					map.put("CHECKUP_KIDNEY", value);
				}
				
			} else if (res.equals("혈액검사-공복혈당")) {
				String value = detailSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getInt("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_BEFORESUGAR"))) {
					map.put("CHECKUP_BEFORESUGAR", value);
				}
				
			} else if (res.equals("혈액검사-혈색소")) {
				String value = detailSigns.getJSONObject("ccr:Test").getJSONObject("ccr:TestResult").getInt("ccr:Value") + "";
				if (!(map.containsKey("CHECKUP_HEMO"))) {
					map.put("CHECKUP_HEMO", value);
				}
				
			}

		}

		System.out.println(map.toString());
		
		return map;
	
	}
    

}
