package com.kh.jsp.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.vo.Member;

import static com.kh.jsp.common.JDBCTemplate.*;

public class MemberDAO {
	
	// sql을 별도로 보관하는 properties 객체 생성하기
	private Properties prop;
	
	public MemberDAO () {
		prop = new Properties();
		
		// 절대경로위치
		String filePath = MemberDAO.class
													  .getResource("/config/member.properties")
													  .getPath();
		try {
			prop.load(new FileReader(filePath));
			// prop으로 불러오겠다.
		} catch (IOException e) { // file not found exception도 포함되있다.
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public int insertMember(Connection con, Member m) throws MemberException {
		// TODO Auto-generated method stub
		
		// 결과 확인을 위한 변수 result 생성
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MemberException("[DAO 에러] : " + e.getMessage());
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection con, Member m) throws MemberException {
		// TODO Auto-generated method stub
		// 1. 사용할 변수 선언
		Member result = null; // 결과를 담을 객체
		
		PreparedStatement pstmt = null; // 쿼리 실행할 객체
		ResultSet rset = null; // Select 결과를 받아 올 객체
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			System.out.println("result 조회 전 : " + result);
			
			if(rset.next()) {
				result = new Member();
				
				result.setUserId(m.getUserId());
				result.setUserPwd(m.getUserPwd());
				result.setUserName(rset.getString("USERNAME"));
				result.setGender(rset.getString("GENDER"));
				result.setAge(rset.getInt(5));
				result.setEmail(rset.getString("email"));
				result.setPhone(rset.getString("phone"));
				result.setAddress(rset.getString("address"));
				result.setHobby(rset.getString("hobby"));
				
			}
			
			System.out.println("result 조회 후 : " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection con, Member m) throws MemberException {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString (1, m.getUserPwd());
			pstmt.setString (2, m.getEmail());
			pstmt.setInt (3, m.getAge());
			pstmt.setString (4, m.getPhone());
			pstmt.setString (5, m.getAddress());
			pstmt.setString (6, m.getHobby());
			pstmt.setString (7, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}		
		
		return result;	
		
	}

	public int deleteMember(Connection con, String userId) throws MemberException {

		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId); // prop set 설치 인덱스
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int idDupCheck(Connection con, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idDupCheck");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

}
