package com.kh.jsp.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.notice.model.dao.NoticeDAO;
import com.kh.jsp.notice.model.vo.Notice;
import static com.kh.jsp.common.JDBCTemplate.*;

public class NoticeService {
	private NoticeDAO nDAO = new NoticeDAO();
	private Connection con;
	
	public ArrayList<Notice> selectList() {
		con = getConnection();
		
		ArrayList<Notice> list = nDAO.selectList(con);
		
		close(con);
		
		return list;
	}

	public Notice selectOne(int nno) {
		con = getConnection();
		
		Notice n = nDAO.selectOne(con, nno);
		
		if(n != null) {
			// 조회수 1증가
			int result = nDAO.updateReadCount(con, nno);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		
		close(con);
		
		return n;
	}

	public int insertNotice(Notice n) {
		con = getConnection();
		
		int result = nDAO.insertNotice(con, n);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Notice updateView(int nno) {
		con = getConnection();
		Notice n = nDAO.selectOne(con, nno);
		
		close(con);
		
		return n;
	}

	public int updateNotice(Notice n) {
		con = getConnection();
		
		int result = nDAO.updateNotice(con, n);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteNotice(int nno) {
		con = getConnection();
		
		int result = nDAO.deleteNotice(con, nno);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Notice> searchNotice(String condition, String keyword) {
		con = getConnection();
		ArrayList<Notice> list = null;
		
		// 컨디션에 뭐라도 내용이 들어왔다면
		// 조건부 검색 실시(제목, 내용 등)
		if(condition.length() > 0) {
			list = nDAO.searchList(con, condition, keyword);
			
		} else { // 검색 조건이 없다면 전체 검색을 실시
			list = nDAO.selectList(con);
		}
		
		close(con);
		
		return list;
	}
	
	
}











