package com.med.login.dao;

import com.med.login.dto.LoginDto;

public interface LoginDao {
	
	//로그인
	public LoginDto login(String member_id, String member_pw);
	public LoginDto snsLogin(String member_email);
	
	//sns 회원 여부
	public int snsCheck(String member_sns, String member_email);
	
	//회원가입-아이디 중복체크, insertMember
	public int idCheck(String member_id);
	public int insertMember(LoginDto dto);
	public int emailCheck(String member_email);
	public int compareEmail(String emailCode, String inputCode);
	
	//내 정보 조회
	public LoginDto selectMember(int member_seq);
	
	//내 정보 수정
	public int updateMember(LoginDto dto);
	
	//회원 탈퇴 > enabled='N'로 update
	public int deleteMember(int member_seq);

}
