package com.kh.backend.common.template;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

public class FileRenamePolicy {
	
	public static String saveFile(MultipartFile upfile, HttpSession session, String path) {
		
		// 1. 원본파일명을 변수에 담기
		String originName = upfile.getOriginalFilename();
		
		// 2. 현재 시간을 연월일시분초 형식으로 뽑기
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 3. 뒤에 붙을 랜덤 5자리 숫자 뽑기 (10000 ~ 99999)
		int random = (int)(Math.random() * 90000 + 10000);
		
		// 4. 원본파일명으로부터 확장자명 뽑기
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 5.합치기
		String changeName = currentTime + random + ext;
		
		// 6. 업로드하고자 하는 서버컴퓨터의 물리적 경로 얻어내기
		String savePath = session.getServletContext().getRealPath(path);
		
		// 7. 경로와 수정파일명을 합쳐서 파일 업로드
		try {
			upfile.transferTo(new File(savePath + changeName));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		// changeName 리턴
		return changeName;
	}

}
