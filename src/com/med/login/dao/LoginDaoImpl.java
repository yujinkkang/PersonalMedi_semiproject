package com.med.login.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.med.login.dto.LoginDto;

import com.med.mybatis.SqlMapSessionFactory;

public class LoginDaoImpl extends SqlMapSessionFactory implements LoginDao {

	@Override
	public LoginDto login(String member_id, String member_pw) {
		
		LoginDto loginDto = new LoginDto(member_id, member_pw);
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession();
			loginDto = session.selectOne("medLogin.login", loginDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return loginDto;
	}
	
	@Override
	public LoginDto snsLogin(String member_email) {
		LoginDto snsDto = new LoginDto(member_email);
		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession();
			snsDto = session.selectOne("medLogin.snsLoginDto", snsDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return snsDto;
	}

	@Override
	public int idCheck(String member_id) {
		int res = 0;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("idRegist.idCheck", member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(res);
		return res;
	}
	
	@Override
	public int emailCheck(String member_email) {
		int res = 0; 
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("idRegist.emailCheck", member_email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(res);
		return res;
	}
	
	@Override
	public int compareEmail(String emailCode, String inputCode) {
		int res = 0;
		if (emailCode.equals(inputCode)) {
			res = 1;
			return res;
		} else {
			return res;
		}
	}
	
	@Override
	public int snsCheck(String member_sns, String member_email) {
		SqlSession session = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_sns", member_sns);
		map.put("member_email", member_email);
		int res = 0;
		
		if(member_sns.equals("N")) {
			///////////
			
		} else if (member_sns.equals("G")) {
			//////////
			
		} else if (member_sns.equals("K")) {
			System.out.println("카카오");
			try {
				session = getSqlSessionFactory().openSession();
				res = session.selectOne("medLogin.checkSNS", map);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		
		return res;
	}


	@Override
	public int insertMember(LoginDto dto) {
		int res = 0;
		SqlSession session = null;
		
		 try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("medLogin.insertMember",dto);
			 
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

	@Override
	public LoginDto selectMember(int member_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(LoginDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(int member_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
