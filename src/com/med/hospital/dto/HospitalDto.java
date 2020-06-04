package com.med.hospital.dto;

public class HospitalDto {
	
	private String sido;
	private String sigungu;
	private String qd;
	private String qn; 
	private String duty_name;
	private String duty_addr;
	private String duty_tel1;
	private String duty_mapimg;
	private String wgs84Lon; //병원경도
	private String wgs84Lat; //병원위도
	private String totaldata;
	
	public HospitalDto() {
	}
	
	

	public HospitalDto(String qn) {
		this.qn = qn;
	}



	public HospitalDto(String sido, String sigungu, String qd, String qn) {
		this.sido = sido;
		this.sigungu = sigungu;
		this.qd = qd;
		this.qn = qn;
	}

	


	public HospitalDto(String sido, String sigungu, String qd, String qn, String duty_name, String duty_addr,
			String duty_tel1, String duty_mapimg, String wgs84Lon, String wgs84Lat, String totaldata) {
		this.sido = sido;
		this.sigungu = sigungu;
		this.qd = qd;
		this.qn = qn;
		this.duty_name = duty_name;
		this.duty_addr = duty_addr;
		this.duty_tel1 = duty_tel1;
		this.duty_mapimg = duty_mapimg;
		this.wgs84Lon = wgs84Lon;
		this.wgs84Lat = wgs84Lat;
		this.totaldata = totaldata;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	public String getQd() {
		return qd;
	}

	public void setQd(String qd) {
		this.qd = qd;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getDuty_name() {
		return duty_name;
	}

	public void setDuty_name(String duty_name) {
		this.duty_name = duty_name;
	}

	public String getDuty_addr() {
		return duty_addr;
	}

	public void setDuty_addr(String duty_addr) {
		this.duty_addr = duty_addr;
	}

	public String getDuty_tel1() {
		return duty_tel1;
	}

	public void setDuty_tel1(String duty_tel1) {
		this.duty_tel1 = duty_tel1;
	}

	public String getDuty_mapimg() {
		return duty_mapimg;
	}

	public void setDuty_mapimg(String duty_mapimg) {
		this.duty_mapimg = duty_mapimg;
	}

	public String getWgs84Lon() {
		return wgs84Lon;
	}

	public void setWgs84Lon(String wgs84Lon) {
		this.wgs84Lon = wgs84Lon;
	}

	public String getWgs84Lat() {
		return wgs84Lat;
	}

	public void setWgs84Lat(String wgs84Lat) {
		this.wgs84Lat = wgs84Lat;
	}

	public String getTotaldata() {
		return totaldata;
	}

	public void setTotaldata(String totaldata) {
		this.totaldata = totaldata;
	}

}
