package com.kh.jsp.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	private static final long serialVersionUID = 4885L;

	// 1. 필드 변수 : DB 의 컬럼과 자바객체 또는 변수 를 이어주는 역할 
	
	// ** 
	// 만약 DB 테이블 정보와 다르게 
	// VO에 더 많은 값을 담거나 DB의 JOIN한 
	// 정보를 담고자 한다면, VO의 필드 변수는
	// 관련 테이블과 반드시 1:1 연결을 시키지 않아도 된다.
	// 즉, 컬럼 : 필드변수 != 1 : 1
	
	private int bno;					// 번호
	private int btype;				// 유형
	private String btitle;			// 제목
	private String bcontent;	// 내용
	private String bwriter;		// 저자
	private String userId;			// 작성자 아이디
	private int bcount;				// 조회수
	private String boardfile;	// 첨부파일
	private Date bdate;			// 작성일
	private String status;			// 게시글 삭제 여부 ( Y : 일반, N : 삭제함 )
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(int bno, int btype, String btitle, String bcontent, String bwriter, String userId, int bcount,
			String boardfile, Date bdate, String status) {
		super();
		this.bno = bno;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.userId = userId;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		this.status = status;
	}

	public Board(String btitle, String bcontent, String bwriter, String boardfile) {
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.boardfile = boardfile;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btype=" + btype + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter="
				+ bwriter + ", userId=" + userId + ", bcount=" + bcount + ", boardfile=" + boardfile + ", bdate="
				+ bdate + ", status=" + status + "]";
	}

	public int getBno() {
		return bno;
	}

	public int getBtype() {
		return btype;
	}

	public String getBtitle() {
		return btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public String getUserId() {
		return userId;
	}

	public int getBcount() {
		return bcount;
	}

	public String getBoardfile() {
		return boardfile;
	}

	public Date getBdate() {
		return bdate;
	}

	public String getStatus() {
		return status;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}	
	