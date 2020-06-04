package com.med.medicine.dto;

import java.sql.Date;

public class MedicineDto {
	
	private int mydrug_seq;
	private int member_seq;
	private String drug_code;
	private String mydrug_name;
	private String mydrug_about;
	private String mydrug_image;
	private Date mydrug_date;
	
	public MedicineDto() {}
	
	public MedicineDto(int mydrug_seq, int member_seq, String drug_code, String mydrug_name, 
			String mydrug_about, String mydrug_image, Date mydrug_date) {
		this.mydrug_seq = mydrug_seq;
		this.member_seq = member_seq;
		this.drug_code = drug_code;
		this.mydrug_name = mydrug_name;
		this.mydrug_about = mydrug_about;
		this.mydrug_image = mydrug_image;
		this.mydrug_date = mydrug_date;
	}
	
	//insert 할 때 쓸 dto
	public MedicineDto(int member_seq, String drug_code, String mydrug_name, String mydrug_about, String mydrug_image) {
		this.member_seq = member_seq;
		this.drug_code = drug_code;
		this.mydrug_name = mydrug_name;
		this.mydrug_about = mydrug_about;
		this.mydrug_image = mydrug_image;
	}
	
	//내가 등록한 약품 목록 볼 때 쓸 dto
	public MedicineDto(int member_seq, String mydrug_name, String mydrug_about, String mydrug_image, Date mydrug_date) {
		super();
		this.member_seq = member_seq;
		this.mydrug_name = mydrug_name;
		this.mydrug_about = mydrug_about;
		this.mydrug_image = mydrug_image;
		this.mydrug_date = mydrug_date;
	}

	public int getMydrug_seq() {
		return mydrug_seq;
	}

	public void setMydrug_seq(int mydrug_seq) {
		this.mydrug_seq = mydrug_seq;
	}

	public int getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}

	public String getDrug_code() {
		return drug_code;
	}

	public void setDrug_code(String drug_code) {
		this.drug_code = drug_code;
	}

	public String getMydrug_name() {
		return mydrug_name;
	}

	public void setMydrug_name(String mydrug_name) {
		this.mydrug_name = mydrug_name;
	}

	public String getMydrug_about() {
		return mydrug_about;
	}

	public void setMydrug_about(String mydrug_about) {
		this.mydrug_about = mydrug_about;
	}

	public Date getMydrug_date() {
		return mydrug_date;
	}

	public void setMydrug_date(Date mydrug_date) {
		this.mydrug_date = mydrug_date;
	}

	public String getMydrug_image() {
		return mydrug_image;
	}

	public void setMydrug_image(String mydrug_image) {
		this.mydrug_image = mydrug_image;
	}
	
}
