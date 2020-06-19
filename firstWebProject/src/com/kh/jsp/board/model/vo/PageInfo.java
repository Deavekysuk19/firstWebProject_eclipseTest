package com.kh.jsp.board.model.vo;

import java.io.Serializable;

public class PageInfo implements Serializable {
	
	// 페이지 처리와 관련된 변수를 모두 담는 객체
    private int currentPage;
    private int listCount;
    private int limit;
    private int maxPage;
    private int startPage;
    private int endPage;
	   
	public PageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageInfo(int currentPage, int listCount, int limit, int maxPage, int startPage, int endPage) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public int getLimit() {
		return limit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	   
		   
   
}
