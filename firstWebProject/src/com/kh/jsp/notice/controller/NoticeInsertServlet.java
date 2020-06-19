package com.kh.jsp.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.notice.model.service.NoticeService;
import com.kh.jsp.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/nInsert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항의 제목, 작성자, 내용 받기
		String ntitle = request.getParameter("title");
		String writer = request.getParameter("userId");
		String content = request.getParameter("content");
		
		// 날짜의 경우 화면에서 전달할 때
		// 만자열 형태로 들어오게 된다.
		String date = request.getParameter("date");
		
		System.out.println("날짜 값 확인 : " + date);
		Date writeDate = new Date(new GregorianCalendar().getTimeInMillis());		
		
		// 2020-05-26 으로 뜬다면, 2020, 05, 26 개별적으로 나눠야한다.
		
		if(date != null && ! date.equals("")) {
			
			String [] dateArr = date.split("-");
			int [] intArr = new int[dateArr.length];
			
			for(int i = 0 ; i < dateArr.length ; i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);			
			}
			
			// writeDate = new Date(intArr[0], intArr[1] - 1, intArr[2]);
			writeDate = new Date(new GregorianCalendar(
												intArr[0], intArr[1] - 1, intArr[2]
					).getTimeInMillis());
		}
		
		// 공지사항 등록을 위한 Notice 객체 생성
		Notice n = new Notice();
		
		n.setNtitle(ntitle);
		n.setNcontent(content);
		n.setNwriter(writer);
		n.setNdate(writeDate);
		
		NoticeService ns = new NoticeService();
		
		int result = ns.insertNotice(n);
		
		if(result > 0) {
			response.sendRedirect("selectList.no");
		} else {
			request.setAttribute("error-msg", "공지ㅅ ㅏ항 등록 실패 !");
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
