package com.kh.chap02_tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// 클라이언트용 프로그램
public class ClientProgram {
	
	/*
	 * * Client 측 프로그램 흐름
	 * 1. 연결요청하고자 하는 서버의 정보 변수로 세팅하기(ip, port)
	 * 2. 서버로 연결요청 보내기(Socket 객체생성구문) --> 서버 측 프로그램 4단계에 매칭됨(요청 accept)
	 * 3. ~~ accept 잘 됐으면 Socket 객체가 잘 생성된 것(클라이언트 쪽 대문 열림) ~~
	 * 4. 서버와 통신할 수 있는 입출력스트림을 Socket 객체로부터 얻어내기
	 * 5. 보조스트림을 추가하여 성능개선
	 * 6. 스트림을 통해서 데이터 읽고 쓰기
	 * 7. 통신종료절차
	 * 
	 */
	
	public static void main(String[] args) {
		
		System.out.println("~~~~ 까까오 talk ~~~~");
		
		Scanner sc = new Scanner(System.in);
		
		Socket socket = null; // 0단계
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			
			String serverIp = "127.0.0.1"; // 1단계~
			int port = 3000;
			
			socket = new Socket(serverIp, port); // 2단계
			
			if(socket != null) { // 3단계
				
				System.out.println("서버 연결 성공!");
				
				br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 4+5단계~
				
				pw = new PrintWriter(socket.getOutputStream());
				
				while(true) { // 6-0단계
					
					System.out.print("서버에게 보낼 내용: "); // 6-1단계~
					String sendMessage = sc.nextLine();
					pw.println(sendMessage);
					
					pw.flush(); // 6-2단계
					
					String message = br.readLine(); // 6-3단계~
					System.out.println("서버: " + message);
				}
			}
		
		} catch(IOException e) {
			
			e.printStackTrace();
			
		} finally { // 7단계
			
			try {
				pw.close();
				br.close();
				socket.close();
				
			} catch(IOException e) {
				
				e.printStackTrace();
			}
		}

	}
	
	// 1단계 요청하고자 하는 서버의 정보(ip주소, port번호) 변수로 세팅
	// 		ip주소(서버 프로그램을 구동시키는 pc의, 예로 내 pc): 직접 or 127.0.0.1(루프백 ip) or localhost
	//		port번호: 3000 (서버 프로그램 참고)
	// 2단계 서버로 연결요청 보내기(== Socket 객체를 생성하겠다.)
	//		누구한테 보낼건지 상대 ip주소, port번호 넘기면서
	// 3단계 ~~ accept 잘 됐으면 Socket 객체 생성됨 ~~ (연결 안되면 객체생성 안됨: == null)
	//		if 사용하기. Unchecked 오류인 NullPointerException 예방
	// 4단계 서버와 통신할 수 있는 입출력스트림 생성(Socket 객체로부터 얻어내기)
	// 5단계 보조스트림 추가하여 성능개선
	
	// - 입력용 스트림(서버로부터 전달받은 데이터 읽어들이는 스트림)
	// > 서버측의 출력용 스트림과 스무스하게 이어질 것!!
	// socket.getInputStream() + InputStreamReader 객체 + BufferedReader 객체
	
	// - 출력용 스트림(서버로 메시지를 내보내는 스트림)
	// > 서버측의 입력용 스트림과 스무스하게 이어질 것!!
	// socket.getOutputStream() + PrintWriter 객체
	
	// 6-0단계 스트림을 통해 데이터 읽고쓰기(무한반복문 안에서 진행)
	// 6-1단계 서버로 데이터 내보내기 PrintWriter
	// 6-2단계 현재 스트림에 남아있는 데이터를 내보내기 (서버측의 7-1단계 데이터를 받게끔)
	// 6-3단계 서버로부터 전달된 데이터 읽기
	// 7단계 통신절차종료(자원반납) Socket -> BufferedReader -> PrintWriter 역순으로.
	// 0단계 null로 초기화
}



























