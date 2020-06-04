package com.med.check.dto;

public class CheckDto {
	
	private int checkup_seq;
	private int member_seq;
	private String checkup_type;
	private String checkup_year;
	private String checkup_month;
	private String checkup_day;
	private String checkup_regist;
	private String checkup_height;
	private String checkup_weight;
	private String checkup_leye;
	private String checkup_reye;
	private String checkup_lhear;
	private String checkup_rhear;
	private String checkup_maxblood;
	private String checkup_minblood;
	private String checkup_kidney;
	private String checkup_hemo;
	private String checkup_fundo;
	private String checkup_beforesugar;
	private String checkup_aftersugar;
	
	public CheckDto() {
		super();
	}

	public CheckDto(int checkup_seq, int member_seq, String checkup_type, String checkup_year, String checkup_month,
			String checkup_day, String checkup_regist, String checkup_height, String checkup_weight,
			String checkup_leye, String checkup_reye, String checkup_lhear, String checkup_rhear,
			String checkup_maxblood, String checkup_minblood, String checkup_kidney, String checkup_hemo,
			String checkup_fundo, String checkup_beforesugar, String checkup_aftersugar) {
		super();
		this.checkup_seq = checkup_seq;
		this.member_seq = member_seq;
		this.checkup_type = checkup_type;
		this.checkup_year = checkup_year;
		this.checkup_month = checkup_month;
		this.checkup_day = checkup_day;
		this.checkup_regist = checkup_regist;
		this.checkup_height = checkup_height;
		this.checkup_weight = checkup_weight;
		this.checkup_leye = checkup_leye;
		this.checkup_reye = checkup_reye;
		this.checkup_lhear = checkup_lhear;
		this.checkup_rhear = checkup_rhear;
		this.checkup_maxblood = checkup_maxblood;
		this.checkup_minblood = checkup_minblood;
		this.checkup_kidney = checkup_kidney;
		this.checkup_hemo = checkup_hemo;
		this.checkup_fundo = checkup_fundo;
		this.checkup_beforesugar = checkup_beforesugar;
		this.checkup_aftersugar = checkup_aftersugar;
	}
	
	//기본 검사
	public CheckDto(int member_seq, String checkup_type, String checkup_year, String checkup_month, String checkup_day,
			String checkup_regist, String checkup_height, String checkup_weight, String checkup_leye,
			String checkup_reye, String checkup_lhear, String checkup_rhear, String checkup_maxblood,
			String checkup_minblood, String checkup_kidney, String checkup_hemo, String checkup_beforesugar) {
		this.member_seq = member_seq;
		this.checkup_type = checkup_type;
		this.checkup_year = checkup_year;
		this.checkup_month = checkup_month;
		this.checkup_day = checkup_day;
		this.checkup_regist = checkup_regist;
		this.checkup_height = checkup_height;
		this.checkup_weight = checkup_weight;
		this.checkup_leye = checkup_leye;
		this.checkup_reye = checkup_reye;
		this.checkup_lhear = checkup_lhear;
		this.checkup_rhear = checkup_rhear;
		this.checkup_maxblood = checkup_maxblood;
		this.checkup_minblood = checkup_minblood;
		this.checkup_kidney = checkup_kidney;
		this.checkup_hemo = checkup_hemo;
		this.checkup_beforesugar = checkup_beforesugar;
	}
	
	//개인 검사 dto
	public CheckDto(int member_seq, String checkup_type, String checkup_year, String checkup_month, String checkup_day,
			String checkup_regist, String checkup_height, String checkup_weight, String checkup_maxblood,
			String checkup_minblood, String checkup_kidney) {
		this.member_seq = member_seq;
		this.checkup_type = checkup_type;
		this.checkup_year = checkup_year;
		this.checkup_month = checkup_month;
		this.checkup_day = checkup_day;
		this.checkup_regist = checkup_regist;
		this.checkup_height = checkup_height;
		this.checkup_weight = checkup_weight;
		this.checkup_maxblood = checkup_maxblood;
		this.checkup_minblood = checkup_minblood;
		this.checkup_kidney = checkup_kidney;
	}
	
	//정밀 검사 dto
	public CheckDto(int member_seq, String checkup_type, String checkup_year, String checkup_month, String checkup_day,
			String checkup_fundo, String checkup_beforesugar, String checkup_aftersugar) {
		super();
		this.member_seq = member_seq;
		this.checkup_type = checkup_type;
		this.checkup_year = checkup_year;
		this.checkup_month = checkup_month;
		this.checkup_day = checkup_day;
		this.checkup_fundo = checkup_fundo;
		this.checkup_beforesugar = checkup_beforesugar;
		this.checkup_aftersugar = checkup_aftersugar;
	}
	

	public int getCheckup_seq() {
		return checkup_seq;
	}



	public void setCheckup_seq(int checkup_seq) {
		this.checkup_seq = checkup_seq;
	}

	public int getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}

	public String getCheckup_type() {
		return checkup_type;
	}

	public void setCheckup_type(String checkup_type) {
		this.checkup_type = checkup_type;
	}

	public String getCheckup_year() {
		return checkup_year;
	}

	public void setCheckup_year(String checkup_year) {
		this.checkup_year = checkup_year;
	}

	public String getCheckup_month() {
		return checkup_month;
	}

	public void setCheckup_month(String checkup_month) {
		this.checkup_month = checkup_month;
	}

	public String getCheckup_day() {
		return checkup_day;
	}

	public void setCheckup_day(String checkup_day) {
		this.checkup_day = checkup_day;
	}

	public String getCheckup_regist() {
		return checkup_regist;
	}

	public void setCheckup_regist(String checkup_regist) {
		this.checkup_regist = checkup_regist;
	}

	public String getCheckup_height() {
		return checkup_height;
	}

	public void setCheckup_height(String checkup_height) {
		this.checkup_height = checkup_height;
	}

	public String getCheckup_weight() {
		return checkup_weight;
	}

	public void setCheckup_weight(String checkup_weight) {
		this.checkup_weight = checkup_weight;
	}

	public String getCheckup_leye() {
		return checkup_leye;
	}

	public void setCheckup_leye(String checkup_leye) {
		this.checkup_leye = checkup_leye;
	}

	public String getCheckup_reye() {
		return checkup_reye;
	}

	public void setCheckup_reye(String checkup_reye) {
		this.checkup_reye = checkup_reye;
	}

	public String getCheckup_lhear() {
		return checkup_lhear;
	}

	public void setCheckup_lhear(String checkup_lhear) {
		this.checkup_lhear = checkup_lhear;
	}

	public String getCheckup_rhear() {
		return checkup_rhear;
	}

	public void setCheckup_rhear(String checkup_rhear) {
		this.checkup_rhear = checkup_rhear;
	}

	public String getCheckup_maxblood() {
		return checkup_maxblood;
	}

	public void setCheckup_maxblood(String checkup_maxblood) {
		this.checkup_maxblood = checkup_maxblood;
	}

	public String getCheckup_minblood() {
		return checkup_minblood;
	}

	public void setCheckup_minblood(String checkup_minblood) {
		this.checkup_minblood = checkup_minblood;
	}

	public String getCheckup_kidney() {
		return checkup_kidney;
	}

	public void setCheckup_kidney(String checkup_kidney) {
		this.checkup_kidney = checkup_kidney;
	}

	public String getCheckup_hemo() {
		return checkup_hemo;
	}

	public void setCheckup_hemo(String checkup_hemo) {
		this.checkup_hemo = checkup_hemo;
	}

	public String getCheckup_fundo() {
		return checkup_fundo;
	}

	public void setCheckup_fundo(String checkup_fundo) {
		this.checkup_fundo = checkup_fundo;
	}

	public String getCheckup_beforesugar() {
		return checkup_beforesugar;
	}

	public void setCheckup_beforesugar(String checkup_beforesugar) {
		this.checkup_beforesugar = checkup_beforesugar;
	}

	public String getCheckup_aftersugar() {
		return checkup_aftersugar;
	}

	public void setCheckup_aftersugar(String checkup_aftersugar) {
		this.checkup_aftersugar = checkup_aftersugar;
	}

}
