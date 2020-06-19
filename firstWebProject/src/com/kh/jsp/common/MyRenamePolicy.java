package com.kh.jsp.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyRenamePolicy extends DefaultFileRenamePolicy {

	@Override
	public File rename(File oldFile) { 
		// 메소드 역할 : 파일의 정보(oldFile)를 받아와서
		// 우리가 지정한 새로운 이름의 파일을 만드는 메소드
		// test.jpg → .jpg 떼고 text 만 받아와
		// test → 20200529 (yyyyMMdd_HHmmss(임의의 랜덤 숫자(동시전송될때) 1000자리)
		
		// 컴퓨터의 시간을 밀리세컨단위로 가져오기
		long currentTimes = System.currentTimeMillis();
		
		// 앞자리 시간 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss"); // 대문자는 24시간으로 처리
		
		// 뒷자리 랜덤 숫자 추출
		int randomNumber = (int)(Math.random() * 10000); // 천자리
		
		// 확장자 명만 제외하고 이름 바꾸기
		String name = oldFile.getName();
		String body = ""; // 파일명 (test.jpg → text)
		String ext = ""; // 확장자 (test.jpg → .jpg)
		
		int dot = name.lastIndexOf('.'); // test.jpg.zip
		
		if(dot != -1) {
			// 확장자가 있다면 , 
			body = name.substring(0, dot);
			ext = name.substring(dot);
		} else {
			// 확장자가 없다면,
			body = name;
		}
		
		// 파일명 바꾸기
		String fileName = sdf.format(new Date(currentTimes))
									+ "_" + randomNumber + ext;
		
		File newFile = new File(oldFile.getParentFile(), fileName); // 파일자체를 가져와서 fileName으로 바꿔주세요
												// oldFile (사본을 떠 변수명지정)
		return newFile;
		
	}

	
	
}
