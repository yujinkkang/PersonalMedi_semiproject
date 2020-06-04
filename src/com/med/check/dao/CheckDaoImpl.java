package com.med.check.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.med.check.dto.CheckDto;
import com.med.mybatis.SqlMapSessionFactory;

public class CheckDaoImpl extends SqlMapSessionFactory implements CheckDao {
	
	private String namespace="medCheck.";
	
	@Override
	public List<CheckDto> checkList() {
		SqlSession session = null;
		List<CheckDto> list = new ArrayList<CheckDto>();
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"checkList");
		} catch (Exception e) {
			System.out.println("[error]:checkList");
			e.printStackTrace();
		}
		session.close();
		
		return list;
	}
	
	public List<CheckDto> checkMemberList(int member_seq) {
		SqlSession session = null;
		List<CheckDto> list = new ArrayList<CheckDto>();
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"checkMemberList", member_seq);
		} catch (Exception e) {
			System.out.println("[error]:checkMemberList");
			e.printStackTrace();
		}
		session.close();
		
		return list;
	}
	
	

	@Override
	public CheckDto selectOne(int checkup_seq) {
		CheckDto dto = null;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace+"selectOne", checkup_seq);
		} catch (Exception e) {
			System.out.println("[error]:selectOne");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int checkBasicInsert(CheckDto dto) { //기본입력(건강검진입력)
		int res =0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"checkBasicInsert", dto);
		} catch (Exception e) {
			System.out.println("[error]:checkBasicInsert");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
	}
	@Override
	public int checkPersonalInsert(CheckDto dto) { //개인입력 
		int res =0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"checkPersonalInsert", dto);
		} catch (Exception e) {
			System.out.println("[error]:checkPersonalInsert");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
		
	}//개인입력
	
	@Override
	public int checkDetailInsert(CheckDto dto) { //정밀입력
		int res =0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"checkDetailInsert", dto);
		} catch (Exception e) {
			System.out.println("[error]:checkDetailInsert");
			e.printStackTrace();
		}finally {
			session.close();
		}
		return res;
		
	}

	@Override
	public int checkUpdate(CheckDto dto) {
		return 0;
	}

	@Override
	public int checkDelete(int checkup_seq) {
		return 0;
	}
	
	@Override
	public List<CheckDto> checkDetail(int member_seq) {
		SqlSession session = null;
		List<CheckDto> list = new ArrayList<CheckDto>();
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"checkDetail", member_seq);
			System.out.println(list+"안된다");
		} catch (Exception e) {
			System.out.println("[error]:checkDetail");
			e.printStackTrace();
		}
		session.close();
		
		return list;
	
	}
    
	@Override
	public List<CheckDto> checkDetail2(int member_seq) {
		SqlSession session = null;
		List<CheckDto> list = new ArrayList<CheckDto>();
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"checkDetail2", member_seq);
		} catch (Exception e) {
			System.out.println("[error]:checkDetail2");
			e.printStackTrace();
		}
		session.close();
		
		return list;
	
	}

}
