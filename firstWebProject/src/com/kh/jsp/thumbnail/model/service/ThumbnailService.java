package com.kh.jsp.thumbnail.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.jsp.thumbnail.model.dao.ThumbnailDAO;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

import static com.kh.jsp.common.JDBCTemplate.*;

public class ThumbnailService {
	private Connection con;
	private ThumbnailDAO tDAO = new ThumbnailDAO();
	
	public ArrayList<Thumbnail> selectList() {
		con = getConnection();
		
		ArrayList<Thumbnail> list = tDAO.selectList(con);
		
		close(con);
		
		return list;
	}

	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		con = getConnection();
		int result = 0;
		
		// 1. 사진 게시글 추가 쿼리 실행
		int result1 = tDAO.insertThumbnail(con, t);
				
		// 추가된 게시글에 맞게 bno 따와서 첨부파일 각각에 적용하기
		if(result1 > 0) {
			int bno = tDAO.getCurrentBno(con);
			
			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setBno(bno);
				
			}
		}
		
		// 2. 첨부파일 여러개 추가하기
		int result2 = 0;
		for(int i = 0 ; i < list.size() ; i++) {
			// 첫번째 이미지는 대표 이미지로, (flevel == 0)
			// 나머지는 서브 이미지 (flevel == 1)
//			if(i == 0) {
//				list.get(i).setFlevel(0);
//			} else {
//				list.get(i).setFlevel(1);
//			}
			list.get(i).setFlevel(i == 0 ? 0 : 1);
			
			result2 = tDAO.insertAttatchment(con, list.get(i));
			
			if(result2 <= 0) break; // 잘못 실행된 행이 있다면 . . .
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}		
		
		return result;
	}

	public HashMap<String, Object> selectOne(int bno) {
		con = getConnection();
		
		HashMap<String, Object> hmap = tDAO.selectOne(con, bno);
		
		close(con);
		
		return hmap;
	}	
	
}
