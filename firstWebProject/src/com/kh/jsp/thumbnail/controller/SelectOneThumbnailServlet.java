package com.kh.jsp.thumbnail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.boardComment.model.service.BoardCommentService;
import com.kh.jsp.boardComment.model.vo.BoardComment;
import com.kh.jsp.thumbnail.model.service.ThumbnailService;

/**
 * Servlet implementation class SelectOneThumbnailServlet
 */
@WebServlet("/selectOne.tn")
public class SelectOneThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		/*		 
		 * 
		 만약에 DB로부터 전달받고자 하는 객체가 여러 개일 경우
		 두가지 방법으로 처리할 수 있다. 
		 (Join해서 여러 테이블의 정보를 가져올 경우)
		 1. 두 객체를 하나로 합친 통합형 VO로 만드는 방법
			  - 한 번 만들어 두면 이용하기 편리하다.
			  - 객체 자체의 메모리 사용률이 올라가
			    서비스 자체가 느려질 수 있다.
		 2. Map 객체를 활용하여 여러 클래스 정보를 모두
		  	Key 와 value로 각각 하나씩 묶어서 사용하는 방법
			  - 여러 객체가 하나의 VO로 필요한 것들만 들어가
			  	언제든지 맞춤형으로 만들 수 있다.
			  	ex) "thumb" : thumbnail, "att" : attachment
		  	 - 각 객체의 내용이 변경될 경우, 이를 직접 확인하기가
		  	  어렵다. (Why? 키로 간접적으로 접근하기 때문이다)
		  	  
		*/
		
		HashMap<String, Object> thumb
		 = new ThumbnailService().selectOne(bno);
		
		ArrayList<BoardComment> clist =
				new BoardCommentService().selectList(bno);
		
		String page = "";
		
		if(thumb != null && thumb.get("attachment")  != null) {
			request.setAttribute("thumbnail", thumb.get("thumbnail"));
			request.setAttribute("fileList", thumb.get("attachment"));
			request.setAttribute("clist", clist);
			page = "views/thumbnail/thumbnailDetail.jsp";
		} else {
			request.setAttribute("error-msg", "사진 게시글 상세보기 실패");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
