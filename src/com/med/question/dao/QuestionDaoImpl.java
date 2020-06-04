package com.med.question.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.med.mybatis.SqlMapSessionFactory;
import com.med.question.biz.QuestionBiz;
import com.med.question.biz.QuestionBizImpl;
import com.med.question.dto.QuestionDto;

public class QuestionDaoImpl extends SqlMapSessionFactory implements QuestionDao {

	@Override
	public int totalCount() {
		SqlSession session = null;
		int total_count = 0;
		
		try {
			session = getSqlSessionFactory().openSession();
			total_count = session.selectOne("question.countAll");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total_count;
	}
	
	public int totalCount(QuestionDto dto) {
		SqlSession session = null;
		int total_count = 0;
		String searchKeyword = "%" + dto.getSearchKeyword() + "%";

		if (dto.getSearchOption().equals("0")) {
			try {
				session = getSqlSessionFactory().openSession();
				total_count = session.selectOne("question.searchCount0", searchKeyword);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		} else if (dto.getSearchOption().equals("1")) {
			try {
				session = getSqlSessionFactory().openSession();
				total_count = session.selectOne("question.searchCount1", searchKeyword);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		} else if (dto.getSearchOption().equals("2")) {
			try {
				session = getSqlSessionFactory().openSession();
				total_count = session.selectOne("question.searchCount2", searchKeyword);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		
		return total_count;
	}
	
	@Override
	public List<QuestionDto> selectList(int recordStartNum, int recordEndNum) {
		SqlSession session = null;
		List<QuestionDto> list = null;

		System.out.println("현재 페이지 시작 RNUM:" + recordStartNum);
		System.out.println("현재 페이지 마지막 RNUM:" + recordEndNum);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("recordStartNum", recordStartNum);
		map.put("recordEndNum", recordEndNum);

		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList("question.countList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	
	@Override
	public List<QuestionDto> selectList(QuestionDto dto, int recordStartNum, int recordEndNum) {
		SqlSession session = null;
		List<QuestionDto> list = null;
		String searchKeyword = "%" + dto.getSearchKeyword() + "%";

		Map<String, String> map = new HashMap<String, String>();
		map.put("searchKeyword", searchKeyword);
		map.put("recordStartNum", recordStartNum+"");
		map.put("recordEndNum", recordEndNum+"");
		
		if (dto.getSearchOption().equals("0")) {
			
			try {
				session = getSqlSessionFactory().openSession();
				list = session.selectList("question.optionList0", map);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		} else if (dto.getSearchOption().equals("1")) {
			
			try {
				session = getSqlSessionFactory().openSession();
				list = session.selectList("question.optionList1", map);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		} else if (dto.getSearchOption().equals("2")) {
			
			try {
				session = getSqlSessionFactory().openSession();
				list = session.selectList("question.optionList2", map);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		}

		return list;
	}

	@Override
	public QuestionDto selectOne(int question_boardno) {
		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		QuestionDto dto = session.selectOne("question.selectOne", question_boardno);
		session.close();
		return dto;
	}
	

	@Override
	public int insert(QuestionDto dto) {
		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
	
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.insert("question.insert", dto);
		if(res>0) {
		session.commit();
		}
		session.close();
		
		return res;
	}

	@Override
	public int update(QuestionDto dto) {
		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.update("question.update", dto);
		if(res>0) {
			session.commit();
			}
			session.close();
			
			return res;
		}

	@Override
	public int delete(int question_boardno) {

		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.delete("question.delete", question_boardno);
		if(res>0) {
			session.commit();
			}
			session.close();
			
			return res;
		}

	public int questionUpdate(int question_parentboardno) {
		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
		
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.update("question.updateQuestion", question_parentboardno);
		if(res>0) {
			session.commit();
			}
			session.close();
			
			return res;
		}
	
	public int questionInsert(QuestionDto dto) {
		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.insert("question.insertQuestion", dto);
		if(res>0) {
		session.commit();
		}
		session.close();
		
		return res;
	}
	
	public int updateReadcount(int question_boardno) {
		String resource = "com/med/mybatis/config.xml";
		InputStream inputStream =null;
		try {
			inputStream= Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.update("question.updateReadcount", question_boardno);
		if(res>0) {
		session.commit();
		}
		session.close();
		
		return res;
	}



}
