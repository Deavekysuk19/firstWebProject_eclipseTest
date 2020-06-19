package com.kh.jsp.boardComment.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jsp.boardComment.model.dao.BoardCommentDAO;
import com.kh.jsp.boardComment.model.vo.BoardComment;

import static com.kh.jsp.common.JDBCTemplate.*;

public class BoardCommentService {
	private Connection con;
	private BoardCommentDAO bcoDAO = new BoardCommentDAO();
	
	public int insertComment(BoardComment bco) {
		con = getConnection();
		
		int result = bcoDAO.insertComment(con, bco);
		
		if( result >  0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<BoardComment> selectList(int bno) {
		con = getConnection();
		ArrayList<BoardComment> clist = bcoDAO.selectList(con, bno);
		
		close(con);
		
		return clist;
	}

	public int deleteComment(int cno) {
		
		con = getConnection();
				
		int result = bcoDAO.deleteComment(con, cno);
		
		if(result > 0) commit (con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int updateComment(BoardComment bco) {
		con = getConnection();
		
		int result = bcoDAO.updateComment(con, bco);
		
		if( result >  0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
}
