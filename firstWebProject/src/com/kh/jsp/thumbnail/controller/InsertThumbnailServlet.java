package com.kh.jsp.thumbnail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.common.MyRenamePolicy;
import com.kh.jsp.thumbnail.model.service.ThumbnailService;
import com.kh.jsp.thumbnail.model.vo.Attachment;
import com.kh.jsp.thumbnail.model.vo.Thumbnail;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class InsertThumbnailServlet
 */
@WebServlet("/tInsert.tn")
public class InsertThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertThumbnailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 멀티파트타입/폼데이터로 전달받았는지 확인.
		if( ! ServletFileUpload.isMultipartContent(request)) {
				// 만약 멀티파트타입으로 보내지 않았다면 
				request.setAttribute("error-msg", "멀티 파트 형식으로 보내주세요");
				request.getRequestDispatcher("views/common/errorPage.jsp")
							.forward(request, response);				
		}
		
		// 2. 전달받을 파일의 최대 크기 설정
		int maxSize = 1024 * 1024 * 10; // 10mb (개인 컴퓨터 최대 한도)
		
		// 3. 저장할 경로 설정하기
		String root = request.getServletContext().getRealPath("/resources");
		String savePath = root + "/thumbnailUploadFiles/";
		
		// 사용자가 저장하는 파일을 서비스 목적에 맞게 
		// 이름을 변경해야 할 경우
		// test.zip ▶ test1.zip 또는 test2.zip 이 아닌
		// test.zip ▶ (서버에서는) 20200529_114600.zip 형식이라 할때
		// ex) 카카오톡 에서 파일을 내려받을 때처럼
		// DefaultFileRenamePolicy 클래스를 상속 받아
		// 우리만의 방식으로 정의할 수 있다.
		MultipartRequest mre = new MultipartRequest(
															request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
		
		// 다중 파일 업로드 시  처리하는 방법
		
		// 4.변경된 파일명과 원본 파일명 관리할 컬렉션 정의하기
		ArrayList<String> saveFiles = new ArrayList<String>();
		ArrayList<String> originFiles = new ArrayList<String>();
		
		// 폼으로 전송된 파일 이름들 가져오기
		Enumeration<String> files = mre.getFileNames();
		
		while(files.hasMoreElements()) {
			// 각 파일 정보 가져오기
			
			String name = files.nextElement();
			
			saveFiles.add(mre.getFilesystemName(name)); // 변경한 파일명
			originFiles.add(mre.getOriginalFileName(name)); // 원본 파일명
			
			System.out.println("name : " + name);
			System.out.println(saveFiles);
			System.out.println(originFiles);
			
		}
		
		// Thumbnail VO 객체를 생성항 서비스에게 전달하기
		Thumbnail t = new Thumbnail();
		
		t.setBtitle(mre.getParameter("title"));
		t.setBcontent(mre.getParameter("content"));
		t.setBwriter(mre.getParameter("userId"));
		
		// Attachment 객체 생성하여 파일 정보 저장하기
		ArrayList<Attachment> list = new ArrayList<Attachment>();
		
		// 리스트에 파일 목록을 하나씩 저장하기
		for(int i = originFiles.size() - 1 ; i >= 0 ; i--) {
			// 기존에 역순으로 저장된 파일 리스트를 올바른 순서로 
			// 뒤집어 재정렬하기
			Attachment at = new Attachment();
			
			at.setFilepath(savePath);
			at.setOriginname(originFiles.get(i));
			at.setChangename(saveFiles.get(i));
			
			list.add(at);
		}
		
		System.out.println("--------------------");
		System.out.println(t);
		System.out.println(list);
		
		ThumbnailService ts = new ThumbnailService();
		int result = ts.insertThumbnail(t, list);
		
		if(result > 0) {
			response.sendRedirect("selectList.tn");
		} else {
			request.setAttribute("error-msg", "사진 게시글 등록 실패");
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
