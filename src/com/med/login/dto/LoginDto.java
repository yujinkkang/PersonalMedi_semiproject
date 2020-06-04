package com.med.login.dto;

public class LoginDto {
	
	private int member_seq;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_birth;
	private String member_gender;
	private String member_email;
	private String member_addr;
	private String member_phone;
	private String member_enabled;
	private String member_sns;
	
	public LoginDto() {}

	public LoginDto(int member_seq, String member_id, String member_pw, String member_name, String member_birth,
			String member_gender, String member_email, String member_addr, 
			String member_phone, String member_enabled, String member_sns) {
		this.member_seq = member_seq;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_birth = member_birth;
		this.member_gender = member_gender;
		this.member_email = member_email;
		this.member_addr = member_addr;
		this.member_phone = member_phone;
		this.member_enabled = member_enabled;
		this.member_sns = member_sns;
	}
	
	//로그인
	public LoginDto(String member_id, String member_pw) {
		this.member_id = member_id;
		this.member_pw = member_pw;
	}
	
	public LoginDto(String member_email) {
		this.member_email = member_email;
	}
	
	//일반 회원 가입
	public LoginDto(String member_sns, String member_id, String member_pw, String member_name, String member_birth,
			String member_gender, String member_email, String member_addr, 
			String member_phone) {
		this.member_sns = member_sns;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_birth = member_birth;
		this.member_gender = member_gender;
		this.member_email = member_email;
		this.member_addr = member_addr;
		this.member_phone = member_phone;
	}

	public int getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_addr() {
		return member_addr;
	}

	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_enabled() {
		return member_enabled;
	}

	public void setMember_enabled(String member_enabled) {
		this.member_enabled = member_enabled;
	}

	public String getMember_sns() {
		return member_sns;
	}

	public void setMember_sns(String member_sns) {
		this.member_sns = member_sns;
	}

}
