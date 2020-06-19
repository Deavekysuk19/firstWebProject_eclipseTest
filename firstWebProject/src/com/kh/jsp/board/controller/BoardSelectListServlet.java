package com.kh.jsp.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardSelectListServlet
 */
@WebServlet("/selectList.bo")
public class BoardSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 게시판 목록 처리 서블릿
		ArrayList<Board> list = new ArrayList<Board>();
		BoardService bs = new BoardService();
		
		// 페이징(Pagination) 처리
		// 에 필요한 변수들
		// 1, 2, 3, . . . -> 6, 7 ,8 . . .
		int startPage;
		
		// 한 번에 처리할 페이지들 중
		// 가장 마지막 페이지
		// 1~ 5, 6 ~ 10, 11 ~ 15
		int endPage;
		
		// 전체 페이지들 중 가장 끝 페이지
		int maxPage;
		
		// 현재 사용자가 보고 있는 페이지
		int currentPage;
		
		// 한 페이지 당 보여줄 게시글 수
		int limit;
		
		// 처음 접속한 사용자가 볼 페이지 초기화 하기
		currentPage = 1;
		
		// 최대 게시글 10개씩
		limit = 10;
		
		// 만약 사용자가 현재 페이지 정보를
		// 가지고 있다면 currentPage 정보 변경하기
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 총 게시글 갯수 가져오기
		int listCount = bs.getListCount();
		System.out.println("총 게시글 수 : " + listCount); // DAO설정후300개가 뜬다.
		// 30개 페이지 에서
		// 만약 전체 게시글 수가 13개라면
		// 페이지는 2개 나와야 한다!
		// ** 짜투리 게시글도 하나의 페이지를 잡아 먹는다 !
		// 13 → 1.3 → 올림 → 2  로 한다.
		// 마찬가지로 예) 19 → 1.9 → 올림 → 2
		maxPage = (int)((double)listCount/limit + 0.9);
		
		// 한번에 보일 시작 페이지와 끝 페이지 를 계산하기.
		// 시작페이지
		// 1 ~ 10 : 1페이지
		// 11 ~ 20 : 11페이지
		startPage = ((int) ((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		// 끝 페이지
		// 1 ~ 10 : 10
		// 11 ~ 20 : 20
		endPage = startPage + limit - 1; // 1 + 10 - 1
		
		// 만약 마지막 페이지가 전체 기준 페이지 보다
		// 크다면, 즉 총 13개 페이지밖에 안나오는데 20까지 잡았다면
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// ------ 위 토대로 페이지 처리 끝
		
		list = bs.selectList(currentPage, limit);

		String page = null;
		
		if(list != null) {
			
			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage); // 따로 빼서 적기위해 객체생성			
			
			/*
			 * request.setAttribute("currentPage", currentPage);
			 * request.setAttribute("listCount", listCount); ... ...
			 */
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			page = "views/board/boardList.jsp";
			
			
		} else {
			page = "views/board/errorPage.jsp";
			request.setAttribute("error-msg", "게시글 목록 조회 실패");			
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
