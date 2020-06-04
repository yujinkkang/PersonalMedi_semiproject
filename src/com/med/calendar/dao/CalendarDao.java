package com.med.calendar.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.med.calendar.dto.CalendarDto;
import com.med.mybatis.SqlMapSessionFactory;

public class CalendarDao extends SqlMapSessionFactory {

	private String namespace = "medcal.";

	public int calInsert(CalendarDto dto) {
		int res = 0;
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace + "calInsert", dto);
		} catch (Exception e) {
			System.out.println("[error]:calInsert");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return res;

	}


	
	public List<CalendarDto> checkMemberList(int member_seq) {
		SqlSession session = null;
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"calMemberList", member_seq);
		} catch (Exception e) {
			System.out.println("[error]:calMemberList");
			e.printStackTrace();
		}
		session.close();
		
		return list;
	}
	
}
