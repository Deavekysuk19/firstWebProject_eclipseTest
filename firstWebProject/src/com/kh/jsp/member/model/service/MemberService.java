package com.kh.jsp.member.model.service;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.dao.MemberDAO;
import com.kh.jsp.member.model.vo.Member;

import static com.kh.jsp.common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	
	private Connection con;
	private MemberDAO mDAO = new MemberDAO();
	
	public int insertMember(Member m) throws MemberException {
		con = getConnection();
		int result = mDAO.insertMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Member selectMember(Member m) throws MemberException {
		// TODO Auto-generated method stub
		con = getConnection();
		Member result = mDAO.selectMember(con, m);
		
		close(con);
		
		// 로그인실패로 null을 null로 그대로 던져주는걸 방지 
		if(result == null) throw new MemberException("[Service 에러] : 로그인 실패!~ ");
		
		return result;
	}

	public int updateMember(Member m) throws MemberException {
		con = getConnection();
		
		int result = mDAO.updateMember(con, m);
		
		if( result > 0 ) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId) throws MemberException {
		con = getConnection();
		int result = mDAO.deleteMember(con, userId);
		
		if( result > 0 ) commit(con);
		else rollback(con);
		
		close(con);
		
		return result ;
	}

	public int idDupCheck(String id) {
		con = getConnection();
		
		int result = mDAO.idDupCheck(con, id);
		
		close (con);
		
		return result;
	}

}
