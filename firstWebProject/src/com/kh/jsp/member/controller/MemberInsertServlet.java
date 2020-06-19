package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.member.exception.MemberException;
import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/mInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 서비스 연동 서블릿
				// 1. 인코딩
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				
				// 2. 입력한 회원 정보 받아오기
				String userId = request.getParameter("userId");
				
				String userPwd = request.getParameter("userPwd");
				
				String userName = request.getParameter("userName");
				String gender = request.getParameter("gender");
				int age = Integer.parseInt(request.getParameter("age"));
				String email = request.getParameter("email");
//				String phone = request.getParameter("phone");
//				String address = request.getParameter("address");
				
				// ex) 010-1111-2222
				String phone = request.getParameter("tel1") + "-"
									 + request.getParameter("tel2") + "-"
									 + request.getParameter("tel3");
				
				String address = request.getParameter("zipCode") + ", "
					     + request.getParameter("address1") + ", "
					     +request.getParameter("address2"); 
				
				// String[] hobby = request.getParameterValues("hobby"); // 배열은 Values
				
				// 배열을 문자열로 만들기
				// 왜? 결국 들어갈 데이터베이스에 배열을 추가할 수 없으니까 !
				String hobby = String.join(", ", request.getParameterValues("hobby")); // 배열은 Values
				
				// VO 사용하기
				// 회원 가입 시 정보를 하나로 묶어 전달하는 역할
				// 변수들 순서 맞춰서 불러오기
				Member m = new Member(userId, userPwd, userName, gender,
															age, email, phone, address, hobby);
				
				System.out.println("가입 정보 확인 : " + m);
				
				// 회원 가입 실행
				MemberService ms = new MemberService();
				
//				int result = ms.insertMember(m);
				
				try {
					ms.insertMember(m);
									
					// 회원 가입 성공!
					System.out.println("회원 가입 성공!");					
					response.sendRedirect("index.jsp");
					
				} catch(MemberException e) {
					
					System.out.println("회원 가입 실패 !");					
					RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
					
					request.setAttribute("error-msg", "회원 가입 실패");
					request.setAttribute("exception", e);
					
					view.forward(request, response);					
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
