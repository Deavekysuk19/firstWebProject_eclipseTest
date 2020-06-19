package com.kh.jsp.board.controller;

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
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/bInsert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드용 서블릿
		// MultipartRequest
		
		// 1. 전송받을 최대 크기 설정하기
		// 10MB → (Byte 크기로 변환하기)
		int maxSize = 1024 * 1024 * 10; // 10mb
		
		// 2. multipart /form-data 형식으로 전송되었는지 확인
		if(! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", "멀티파트로 전송 안됐다 요");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		} 
		
		// 3. 웹 상의 루트 (최상위) 경로를 활용하여
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 설정한 정보를 바탕으로 멀티파트 객체 생성하기
		// request → MultipartRequest 가 될것입니다
		MultipartRequest mre = new MultipartRequest(
															request, // 속성 변경을 위한 원본 객체
															savePath, // 저장할 파일 경로
															maxSize, // 저장할 파일 최대 크기
															"UTF-8", // 저장할 문자셋
															new DefaultFileRenamePolicy()
															// 만약 폴더에 같은 이름의 파일이
															// 새로 저장될 경우 자동으로 
															// 이름을 바꿔주는 정책
															// ex) 000.txt → 0001.txt → 0002.txt 
				);
		// ----------- 파일 업로드 설정 끝 ! --------------- // 
		
		// 5-1. 기본 전송값 처리하기
		String title = mre.getParameter("title");
		String content = mre.getParameter("content");
		String writer = mre.getParameter("userId");
		
		// 5-2. 파일 전송 후 저장하기
		// 전달받은 파일을 먼저 저장한 후에 해당 파일의 
		// 이름을 가져온다
		String fileName = mre.getFilesystemName("file"); // name tag 속성
		
		// 6. 전달받은 값을 서비스로 넘기기.
		Board b = new Board(title, content, writer, fileName);
		
		int result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect("selectList.bo");
		} else {
			request.setAttribute("error-msg", "게시글 작성 실패 !");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
