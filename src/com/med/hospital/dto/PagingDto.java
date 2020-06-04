package com.med.hospital.dto;

public class PagingDto {

	private int totalData;		// 총 데이터 수
	private int dataPerPage; 	// 페이지당 게시물	
	private int pageCount; 		// 페이지 숫자 		
	private int currentPage; 	// 현재페이지	
	private int startpage;		// 시작페이지
	private int endpage;		// 끝페이지
	private int pagegroup;		// 
	private int totalpage; 		// 전체페이지 
	
	public PagingDto() {
	}

	public PagingDto(int totalData, int dataPerPage, int pageCount, int currentPage, int startpage, int endpage,
			int pagegroup, int totalpage) {
		this.totalData = totalData;
		this.dataPerPage = dataPerPage;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.startpage = startpage;
		this.endpage = endpage;
		this.pagegroup = pagegroup;
		this.totalpage = totalpage;
	}

	public int getTotalData() {
		return totalData;
	}

	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}

	public int getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(int dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getPagegroup() {
		return pagegroup;
	}

	public void setPagegroup(int pagegroup) {
		this.pagegroup = pagegroup;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}



}
