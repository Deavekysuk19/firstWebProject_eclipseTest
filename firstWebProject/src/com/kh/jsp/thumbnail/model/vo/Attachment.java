package com.kh.jsp.thumbnail.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable {
	
	private static final long serialVersionUID = 1123213213L;
	
	private int  fno;
	private int bno;
	private String originname;
	private String changename;
	private String filepath;
	private int flevel;
	private Date uploaddate;
	private String status;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(int fno, int bno, String originname, String changename, String filepath, int flevel,
			Date uploaddate, String status) {
		super();
		this.fno = fno;
		this.bno = bno;
		this.originname = originname;
		this.changename = changename;
		this.filepath = filepath;
		this.flevel = flevel;
		this.uploaddate = uploaddate;
		this.status = status;
	}

	public Attachment(String originname, String changename, String filepath) {
		super();
		this.originname = originname;
		this.changename = changename;
		this.filepath = filepath;
	}

	public int getFno() {
		return fno;
	}

	public int getBno() {
		return bno;
	}

	public String getOriginname() {
		return originname;
	}

	public String getChangename() {
		return changename;
	}

	public String getFilepath() {
		return filepath;
	}

	public int getFlevel() {
		return flevel;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public String getStatus() {
		return status;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public void setChangename(String changename) {
		this.changename = changename;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setFlevel(int flevel) {
		this.flevel = flevel;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}
