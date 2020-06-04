package com.med.pharm.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.med.pharm.dao.PharmDao;

public class PharmBiz {
private PharmDao dao = new PharmDao();
	
	public List<HashMap<String, String>> getPharm(String sido, String sigungu, String qn) throws IOException{
	
		JSONObject xmlJSONobj = dao.getPharm(sido, sigungu, qn);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		JSONArray PharmArray;
		JSONObject PharmObject;
		
		JSONObject PharmCount;
		PharmCount = xmlJSONobj.getJSONObject("response")
									.getJSONObject("body");
		int temp = PharmCount.getInt("totalCount");
		String totalCount = String.valueOf(temp);
		
		
		if(temp > 1) {
			try {
				PharmArray = xmlJSONobj.getJSONObject("response")
											.getJSONObject("body")
											.getJSONObject("items")
											.getJSONArray("item");
			
				
				for(int i = 0; i < PharmArray.length(); i++) {
					
					JSONObject resultJSON = PharmArray.getJSONObject(i);
					
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

				PharmObject = xmlJSONobj.getJSONObject("response")
											.getJSONObject("body")
											.getJSONObject("items")
											.getJSONObject("item");
			
				
					JSONObject resultJSON = PharmObject;
					
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
