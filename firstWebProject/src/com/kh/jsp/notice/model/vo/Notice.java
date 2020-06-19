package com.kh.jsp.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable {

	private static final long serialVersionUID = 8282L;
	
	private int nno;
	private String ntitle;
	private String ncontent;
	private String nwriter;
	private int ncount;
	private Date ndate; // sql import
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nwriter=" + nwriter
				+ ", ncount=" + ncount + ", ndate=" + ndate + "]";
	}
	
	public Notice(int nno, String ntitle, String ncontent, String nwriter, int ncount, Date ndate) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriter = nwriter;
		this.ncount = ncount;
		this.ndate = ndate;
	}

	public Notice(String ntitle, String ncontent, String nwriter) {
		super();
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriter = nwriter;
	}

	public int getNno() {
		return nno;
	}

	public String getNtitle() {
		return ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public String getNwriter() {
		return nwriter;
	}

	public int getNcount() {
		return ncount;
	}

	public Date getNdate() {
		return ndate;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public void setNwriter(String nwriter) {
		this.nwriter = nwriter;
	}

	public void setNcount(int ncount) {
		this.ncount = ncount;
	}

	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}		
	
}
