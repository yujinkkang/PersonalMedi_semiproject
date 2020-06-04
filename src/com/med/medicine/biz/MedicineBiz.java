package com.med.medicine.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.med.medicine.dao.MedicineDao;
import com.med.medicine.dto.MedicineDto;

public class MedicineBiz {
	
	private MedicineDao dao = new MedicineDao();
	
	public List<HashMap<String, String>> getMedicine(String item_name) throws Exception {
		
		JSONObject xmlJSONobj = dao.getMedicine(item_name);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		JSONArray medicineArray;
		try {
				medicineArray = xmlJSONobj.getJSONObject("response")
												.getJSONObject("body")
												.getJSONObject("items")
												.getJSONArray("item");
			for(int i = 0; i < medicineArray.length() ; i++) {
				JSONObject resultJSON = medicineArray.getJSONObject(i);
				
				String item_seq = resultJSON.getInt("ITEM_SEQ") + "";
	        	String item_name_res = resultJSON.getString("ITEM_NAME");
	        	String class_name = resultJSON.getString("CLASS_NAME");
	        	String item_image = resultJSON.getString("ITEM_IMAGE");
	        	
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("ITEM_SEQ", item_seq);
	        	map.put("ITEM_NAME", item_name_res);
	        	map.put("CLASS_NAME", class_name);
	        	map.put("ITEM_IMAGE", item_image);

	        	list.add(map);
	        	
			}
		} catch (JSONException e) {
			System.out.println("없다");
			System.out.println(list);
			return list;
		}

		return list;
		
	}
	
	public Map<String, String> getDUR(String item_name) throws Exception {
		
		JSONObject xmlJSONobj = dao.getDUR(item_name);
		
		int isNull = xmlJSONobj.getJSONObject("response")
				  .getJSONObject("body")
				  .getInt("totalCount");
		
		if (isNull == 0) {
			Map<String, String> map = new HashMap<String, String>();
			
			return map;

		} else {
			JSONObject durObject = xmlJSONobj.getJSONObject("response")
					   .getJSONObject("body")
					   .getJSONObject("items")
					   .getJSONObject("item");
			System.out.println(durObject.toString(4));

			String item_name_res = durObject.getString("ITEM_NAME");		//약품이름
			String effect_name = durObject.getString("EFFECT_NAME");		//(간단나)설명
			String type_name = durObject.getString("TYPE_NAME");			//dur 이름
			String item_seq = durObject.getInt("ITEM_SEQ") + "";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("ITEM_NAME", item_name_res);
			map.put("EFFECT_NAME", effect_name);
			map.put("TYPE_NAME", type_name);
			map.put("ITEM_SEQ", item_seq);
			
			return map;
			
		}

	}
	
	
	public Map<String, String> getMedicineSeq(String item_seq) throws Exception {
		
		JSONObject xmlJSONobj = dao.getMedicineSeq(item_seq);
		JSONObject medicineSeq = xmlJSONobj.getJSONObject("response")
										   .getJSONObject("body")
										   .getJSONObject("items")
										   .getJSONObject("item");
		
		
		String item_name = medicineSeq.getString("ITEM_NAME");
		String class_name = medicineSeq.getString("CLASS_NAME");
		String item_image = medicineSeq.getString("ITEM_IMAGE");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("ITEM_SEQ", item_seq);
		map.put("ITEM_NAME", item_name);
		map.put("CLASS_NAME", class_name);
		map.put("ITEM_IMAGE", item_image);
		
		return map;
		
	}
	
	public int insertMedicine(MedicineDto dto) {
		return dao.insertMember(dto);
	}
	
	public List<MedicineDto> selectMedList(int member_seq) {
		return dao.selectMedList(member_seq);
	}
	
	public int medicineDelete(int mydrug_seq) {
		return dao.medicineDelete(mydrug_seq);
	}
	
	
	
}
