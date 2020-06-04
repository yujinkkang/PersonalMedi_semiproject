package com.med.hospital.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.med.hospital.dao.HospitalDao;

public class HospitalBiz {
	

	private HospitalDao dao = new HospitalDao();
	
	public List<HashMap<String, String>> getHospital(String sido, String sigungu, String qd, String qn) throws IOException{
	
		JSONObject xmlJSONobj = dao.getHospital(sido, sigungu, qd, qn);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		JSONArray hospitalArray;
		JSONObject hospitalObject;
		
		JSONObject hospitalCount;
		hospitalCount = xmlJSONobj.getJSONObject("response")
									.getJSONObject("body");
		int temp = hospitalCount.getInt("totalCount");
		String totalCount = String.valueOf(temp);
		
		
		if(temp > 1) {
			try {
				hospitalArray = xmlJSONobj.getJSONObject("response")
											.getJSONObject("body")
											.getJSONObject("items")
											.getJSONArray("item");
			
				
				for(int i = 0; i < hospitalArray.length(); i++) {
					
					JSONObject resultJSON = hospitalArray.getJSONObject(i);
					
					String dutyName = resultJSON.getString("dutyName");
					String dutyAddr = resultJSON.getString("dutyAddr");
					String dutyTel1 = resultJSON.getString("dutyTel1");
					Double wgs84Lon = resultJSON.getDouble("wgs84Lon"); 
					Double wgs84Lat = resultJSON.getDouble("wgs84Lat");
					
					
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("dutyName", dutyName);
					map.put("dutyAddr", dutyAddr);
					map.put("dutyTel1", dutyTel1);
					map.put("wgs84Lon", wgs84Lon.toString());
					map.put("wgs84Lat", wgs84Lat.toString());
					map.put("totalCount", totalCount);

					
					
					list.add(map);
					
					
				}
				
			} catch (JSONException e) {
//				System.out.println("없다");
//				System.out.println(list);
				e.printStackTrace();
				return list;
			}
			
			
			
		} else if(temp == 1){

				hospitalObject = xmlJSONobj.getJSONObject("response")
											.getJSONObject("body")
											.getJSONObject("items")
											.getJSONObject("item");
			
				
					JSONObject resultJSON = hospitalObject;
					
					String dutyName = resultJSON.getString("dutyName");
					String dutyAddr = resultJSON.getString("dutyAddr");
					String dutyTel1 = resultJSON.getString("dutyTel1");
					Double wgs84Lon = resultJSON.getDouble("wgs84Lon"); 
					Double wgs84Lat = resultJSON.getDouble("wgs84Lat");
					
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("dutyName", dutyName);
					map.put("dutyAddr", dutyAddr);
					map.put("dutyTel1", dutyTel1);
					map.put("wgs84Lon", wgs84Lon.toString());
					map.put("wgs84Lat", wgs84Lat.toString());
					map.put("totalCount", totalCount);

					
					
					list.add(map);
					
					
				}
				
			
//		System.out.println(list);
		
		return list;
	}



}
