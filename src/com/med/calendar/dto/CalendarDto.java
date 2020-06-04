package com.med.calendar.dto;

public class CalendarDto {
	
	private int manage_seq;
	private int member_seq;
	private String title;
	private String start_date;
	private String end_date;
	public CalendarDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CalendarDto(int manage_seq, int member_seq, String title, String start_date, String end_date) {
		super();
		this.manage_seq = manage_seq;
		this.member_seq = member_seq;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public int getManage_seq() {
		return manage_seq;
	}
	public void setManage_seq(int manage_seq) {
		this.manage_seq = manage_seq;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
}
