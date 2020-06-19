package com.kh.jsp.thumbnail.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.thumbnail.model.service.ThumbnailService;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;

/**
 * Servlet implementation class SelectThumbnailListServlet
 */
@WebServlet("/selectList.tn")
public class SelectThumbnailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectThumbnailListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Thumbnail> list = new ArrayList<Thumbnail>();
		
		list = new ThumbnailService().selectList();
		
		String page = null;
		
		if(list != null) {
			request.setAttribute("list", list);
			page = "views/thumbnail/thumbnailList.jsp";			
		} else {
			request.setAttribute("error-msg", "사진 게시판 목록 조회 실패");
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
