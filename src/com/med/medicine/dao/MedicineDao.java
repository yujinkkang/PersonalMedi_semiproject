package com.med.medicine.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.apache.ibatis.session.SqlSession;

import com.med.mybatis.SqlMapSessionFactory;
import com.med.medicine.dto.MedicineDto;

public class MedicineDao extends SqlMapSessionFactory{
	
	public JSONObject getMedicine(String item_name) throws Exception {

		String addr = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey=";
		String serviceKey = "Nyfp8NF7AJwKl8KF%2FyYxDc9xYifn3074ZQBaAFNqcxn7z6a5%2FgqoONZpYiI9E1JFsD%2BlqL0Ptf6rRT4FJgaKDA%3D%3D";
		
		String parameter = "";
			//약품명 UTF-8로 인코딩해서
		parameter = parameter + "&" + "item_name=" + item_name;		//&item_name=약품명으로 넘김
		
		addr = addr + serviceKey + parameter;				//전체 주소
		
		URL url = new URL(addr);
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
        
        return xmlJSONobj;
	}
	
	public JSONObject getMedicineSeq(String item_seq) throws Exception {

		String addr = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey=";
		String serviceKey = "Nyfp8NF7AJwKl8KF%2FyYxDc9xYifn3074ZQBaAFNqcxn7z6a5%2FgqoONZpYiI9E1JFsD%2BlqL0Ptf6rRT4FJgaKDA%3D%3D";
		
		String parameter = "";
		parameter = parameter + "&" + "item_seq=" + item_seq;		//&item_seq=약품번호로 넘김
		
		addr = addr + serviceKey + parameter;				//전체 주소

		URL url = new URL(addr);
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
        
        JSONObject xmlJSONobj = new JSONObject();
        xmlJSONobj = XML.toJSONObject(sb.toString());

        return xmlJSONobj;
	}
	
	public JSONObject getDUR(String item_name) throws Exception {

		String addr = "http://apis.data.go.kr/1470000/DURPrdlstInfoService/getEfcyDplctInfoList?ServiceKey=";
		String serviceKey = "Nyfp8NF7AJwKl8KF%2FyYxDc9xYifn3074ZQBaAFNqcxn7z6a5%2FgqoONZpYiI9E1JFsD%2BlqL0Ptf6rRT4FJgaKDA%3D%3D";
		
		//&typeName=효능군중복&itemName=세레콕시브
		String parameter = "";
		String durType = "효능군중복";
		//URLEncoder.encode(item_seq, "UTF-8")
		
		parameter = parameter + "&" + "typeName=" + URLEncoder.encode("효능군중복", "UTF-8") +
								"&" + "itemName=" + item_name;

		addr = addr + serviceKey + parameter;				//전체 주소

		URL url = new URL(addr);
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
        
        JSONObject xmlJSONobj = new JSONObject();
        xmlJSONobj = XML.toJSONObject(sb.toString());
        
		int isNull = xmlJSONobj.getJSONObject("response")
									  .getJSONObject("body")
									  .getInt("totalCount");
		if (isNull == 0) {
			System.out.println("값이 없음(isNull이 0)");
			return xmlJSONobj;
		} else {
			return xmlJSONobj;
		}
		
	}
	
	public int insertMember(MedicineDto dto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("medInsert.insertMedicine", dto);
			 
			if(res>0) {
				session.commit();
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	public List<MedicineDto> selectMedList(int member_seq) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession();
		List<MedicineDto> list = session.selectList("medInsert.selectList", member_seq);
		session.close();
		return list;
	}
	
	public int medicineDelete(int mydrug_seq) {
		int res = 0;
		SqlSession session = null;
		session = getSqlSessionFactory().openSession(false);		//autocommit X
		res = session.delete("medInsert.deleteMedicine", mydrug_seq);
		
		if (res > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();

		return res;
	}
	
	/*public List<QuestionDto> selectList() {
		String resource = "com/question/db/config.xml";
		InputStream inputStream=null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		List<QuestionDto> list = session.selectList("question.selectList");
		session.close();
		
		return list;
	}*/
	
}
