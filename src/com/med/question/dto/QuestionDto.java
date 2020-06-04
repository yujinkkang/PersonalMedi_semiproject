package com.med.question.dto;

import java.sql.Date;

public class QuestionDto {
	
	private int question_boardno;
	private int member_seq;
	private String member_id;
	
	private int question_groupno;
	private int question_level;
	private int question_titletab;
	private String question_title;
	private String question_content;
	private Date question_regdate;
	private int question_readcount;
	
	//검색
	private int pageNum;
	private String searchKeyword;
	private String searchOption;
	
	public QuestionDto() {}

	public QuestionDto(int question_boardno, int member_seq, String member_id, int question_groupno, int question_level,
			int question_titletab, String question_title, String question_content, Date question_regdate,
			int question_readcount, int pageNum, String searchKeyword, String searchOption) {
		this.question_boardno = question_boardno;
		this.member_seq = member_seq;
		this.member_id = member_id;
		this.question_groupno = question_groupno;
		this.question_level = question_level;
		this.question_titletab = question_titletab;
		this.question_title = question_title;
		this.question_content = question_content;
		this.question_regdate = question_regdate;
		this.question_readcount = question_readcount;
		this.searchKeyword = searchKeyword;
		this.searchOption = searchOption;
	}

	public QuestionDto(String searchOption, String searchKeyword) {
		this.searchOption = searchOption;
		this.searchKeyword = searchKeyword;
	}

	public int getQuestion_boardno() {
		return question_boardno;
	}

	public void setQuestion_boardno(int question_boardno) {
		this.question_boardno = question_boardno;
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

	public int getQuestion_groupno() {
		return question_groupno;
	}

	public void setQuestion_groupno(int question_groupno) {
		this.question_groupno = question_groupno;
	}

	public int getQuestion_level() {
		return question_level;
	}

	public void setQuestion_level(int question_level) {
		this.question_level = question_level;
	}

	public int getQuestion_titletab() {
		return question_titletab;
	}

	public void setQuestion_titletab(int question_titletab) {
		this.question_titletab = question_titletab;
	}

	public String getQuestion_title() {
		return question_title;
	}

	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}

	public String getQuestion_content() {
		return question_content;
	}

	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}

	public Date getQuestion_regdate() {
		return question_regdate;
	}

	public void setQuestion_regdate(Date question_regdate) {
		this.question_regdate = question_regdate;
	}

	public int getQuestion_readcount() {
		return question_readcount;
	}

	public void setQuestion_readcount(int question_readcount) {
		this.question_readcount = question_readcount;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	
	
	
	
}
