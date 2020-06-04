package com.med.login.biz;

import com.med.login.dao.LoginDao;
import com.med.login.dao.LoginDaoImpl;
import com.med.login.dto.LoginDto;

public class LoginBizImpl implements LoginBiz {
	
	private LoginDao dao = new LoginDaoImpl();

	@Override
	public LoginDto login(String member_id, String member_pw) {
		return dao.login(member_id, member_pw);
	}

	@Override
	public LoginDto snsLogin(String member_email) {
		return dao.snsLogin(member_email);
	}

	@Override
	public int snsCheck(String member_sns, String member_email) {
		return dao.snsCheck(member_sns, member_email);
	}
	
	@Override
	public int idCheck(String member_id) {
		System.out.println(dao.idCheck(member_id));
		return dao.idCheck(member_id);
	}
	
	@Override
	public int emailCheck(String member_email) {
		System.out.println(dao.emailCheck(member_email));
		return dao.emailCheck(member_email);
	}
	
	@Override
	public int compareEmail(String emailCode, String inputCode) {
		return dao.compareEmail(emailCode, inputCode);
	}

	@Override
	public int insertMember(LoginDto dto) {
		return dao.insertMember(dto);
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
