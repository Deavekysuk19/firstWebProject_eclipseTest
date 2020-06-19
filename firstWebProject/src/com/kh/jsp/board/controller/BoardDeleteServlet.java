package com.kh.jsp.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/bDelete.bo")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전송받을 최대 크기 설정
		int maxSize = 1024 * 1024 * 10; // 10MB;
		
		// 2. multipart/form-data 형식 확인
		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", 
					             "multipart로 전송되지 않았습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
		
		// 3. 저장할 폴더 위치 설정 (나중에 임시 이관용 폴더로 만들 수도 있음)
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 멀티파트 객체 생성
		MultipartRequest mre = new MultipartRequest(
									request, savePath, maxSize, "UTF-8",
									new DefaultFileRenamePolicy()
				);
		
		int bno = Integer.parseInt(mre.getParameter("bno"));
		
		BoardService bs = new BoardService();
		// Board b = bs.selectOne(bno);
		// new File(b.getBoardfile()).delete();
		
		int result = bs.deleteBoard(bno);
		
		if(result > 0) {
			response.sendRedirect("selectList.bo");
		} else {
			request.setAttribute("error-msg", "게시글 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
