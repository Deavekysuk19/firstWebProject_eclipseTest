package com.kh.jsp.thumbnail.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.jsp.boardComment.model.dao.BoardCommentDAO;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

import static com.kh.jsp.common.JDBCTemplate.*;

public class ThumbnailDAO {
	private Properties prop = null;
	
	public ThumbnailDAO() {
		prop = new Properties();
		
		String filePath = BoardCommentDAO.class
		          .getResource("/config/thumbnail.properties")
		          .getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<Thumbnail> selectList(Connection con) {
		ArrayList<Thumbnail> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Thumbnail>();
			
			while(rset.next()) {
				Thumbnail tn = new Thumbnail();
				
				tn.setBno(rset.getInt("bno"));
				tn.setBtitle(rset.getString("btitle"));
				tn.setBcontent(rset.getString("bcontent"));
				tn.setBwriter(rset.getString("username"));
				tn.setBcount(rset.getInt("bcount"));
				tn.setBdate(rset.getDate("bdate"));
				tn.setBoardfile(rset.getString("changename"));
				
				list.add(tn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertThumbnail");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,  t.getBtitle());
			pstmt.setString(2, t.getBcontent());
			pstmt.setString(3, t.getBwriter());
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getCurrentBno(Connection con) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("currentBno");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return result;
	}

	public int insertAttatchment(Connection con, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttatchment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, at.getBno());
			pstmt.setString(2, at.getOriginname());
			pstmt.setString(3, at.getChangename());
			pstmt.setString(4, at.getFilepath());
			pstmt.setInt(5, at.getFlevel());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public HashMap<String, Object> selectOne(Connection con, int bno) {
		HashMap<String, Object> hmap = null;
		ArrayList<Attachment> list = null;
		Thumbnail t = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Attachment>();
			
			while(rset.next()) {
				t = new Thumbnail();
				
				t.setBno(rset.getInt("bno"));
				t.setBtitle(rset.getString("btitle"));
				t.setBcontent(rset.getString("bcontent"));
				t.setBwriter(rset.getString("username"));
				t.setUserId(rset.getString("bwriter"));
				t.setBcount(rset.getInt("bcount"));
				t.setBdate(rset.getDate("bdate"));
				
				Attachment att = new Attachment();
				
				att.setFno(rset.getInt("fno"));
				att.setOriginname(rset.getString("originname"));
				att.setChangename(rset.getString("changename"));
				att.setFilepath(rset.getString("filepath"));
				att.setFlevel(rset.getInt("flevel"));
				
				list.add(att);				
				
			}
			
			hmap = new HashMap<String, Object>();
			
			hmap.put("thumbnail", t);
			hmap.put("attachment", list);
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hmap;
	}
}
