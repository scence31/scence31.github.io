package com.kh.chap02_tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// 서버용 프로그램 - 항상 먼저 작성
public class ServerProgram {
	/*
	 * * TCP(Transmission Control Protocol): 연결지향적인 통신방법
	 * - 서버와 클라이언트 간 1:1 소켓통신방식 중 하나
	 * - 데이터를 교환하기에 앞서 서버와 클라이언트가 각각 연결되어있어야함
	 *   (항상 서버거 먼저 실행되어 클라이언트의 요청 기다려야함)
	 * - 신뢰성 있는 데이터를 전달 가능
	 * 
	 * 
	 * * 소켓클래스(java.net.Socket)
	 * - 프로세스 간 통신을 담당하는 기능이 정의되어있는 자바 클래스
	 * - 소켓객체가 생성되었다 == 대문이 활짝 열렸다
	 * - 소켓 프로그래밍의 기반이 되는 기반스트림 Input / OutputStream 가지고 있음
	 * 
	 * * 서버소켓클래스
	 * - 소켓 프로그래밍 시 서버쪽 프로그램에서 추가적으로 필요한 객체 타입
	 * - 클라이언트의 연결요청을 기다렸다가 수락해주는(accept) 실질적인 역할을 하는 객체
	 * - 연결요청을 수락하게 되면 바로 Socket 객체를 얻어낼 수 있음(즉 연결요청 수락시 대문이 열림)
	 * 
	 * # 서버쪽 코드에는 소켓객체, 서버소켓객체 둘 다 필요함
	 * 
	 * * Server 측 프로그램 흐름
	 * 1. Port 번호 지정하기(Server 측 프로그램에서 사용)
	 * 2. ServerSocket 객체 생성(Port 번호를 넘기면서) == 포트 결합(bind)
	 * 3. ~~클라이언트로부터 접속 요청이 올 때까지 무한대기~~
	 * 4. 연결요청이 오면 수락(accept) 후 서버 측 소켓객체가 생성됨(대문 열림)
	 * 5. 클라이언트와 통신할 수 있는 입출력스트림객체 생성(소켓객체로부터 얻기, 연결통로 만들어짐) 
	 * 6. 보조스트림 추가해서 성능개선(옵션)
	 * 7. 스트림을 통해 데이터 읽고 쓰기
	 * 8. 통신종료 절차(스트림 close, 소켓 close)
	 * 
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); // 7-2단계
		
		System.out.println("~~~~ 까까오 talk ~~~~");
		
		ServerSocket server = null; // 0단계~
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		int port = 3000; // 1단계
		
		try {
			server = new ServerSocket(port); // 2단계 try
			
			System.out.println("클라이언트 요청 기다리는중..."); // 3단계
			socket = server.accept(); // 4단계
			
			System.out.println(socket.getInetAddress().getHostAddress() + "가 연결을 요청함..."); // 4단계
			
			// socket.getInputStream(); ??
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 5+6단계 (입력용)
			
			pw = new PrintWriter(socket.getOutputStream()); // 5+6단계 (출력용)
			while(true) {	// 7-0단계
				
				String message = br.readLine();	// 7-1단계 ~
				System.out.println("클라이언트: " + message);
				
				System.out.print("클라이언트에게: " ); // 7-2단계 ~
				String sendMessage = sc.nextLine();
				pw.println(sendMessage);
				
				pw.flush(); // 7-3단계(출력용 스트림에만, 무한반복 안에 있어서 사용)
			}
			
			
		} catch(IOException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				pw.close();
				br.close();
				socket.close();
				server.close();
			} catch(IOException e) {
				
				e.printStackTrace();
			}
		}

	}

}

	// 1단계 서버 프로그램에서 사용할 포트번호 먼저 지정(포트번호는 정수값으로)
	// 2단계 ServerSocket 객체 생성(포트번호 넘기면서) - 포트결합(bind)
	// 3단계 ~~클라이언트로부터 접속 요청 올 때까지 대기상태~~
	// 4단계 연결요청이 오면 연결수락: accept() --> 곧바로 Socket 객체 생성됨 --> 대문이 활짝 열림
	//      (ServerSocket 객체에서 제공하는 연결요청)
	// 		서버쪽 프로그램에서는 절대로 Socket 객체를 new 생성자를 통해 직접 생성하지 않음
	//		--> 클라이언트와 연결시 자동으로 얻어지는 것을 그냥 쓰면됨
	//		누구랑 연결되었을까? socket.getInetAddress() 메소드를 통해 알 수 있음(상대 pc정보를 변환)
	//
	// 5단계 클라이언트와 통신할 수 있는 입출력스트림객체 생성(Socket 객체에서 제공하는 놈으로 얻어다 쓸 것)
	// +
	// 6단계 보조스트림을 추가하여 성능 개선

	// - 입력용스트림: 클라이언트가 전달한 메시지 내용을 읽어들이기 위한 스트림(클라이언트-->서버)
	// >> 기반스트림: InputStream 객체 socket.getInputStream()
	// + InputSreamReader 객체(사이즈 호환용 보조스트림 객체)
	// + 보조스트림: BufferedReader 객체
	//
	// # 기반스트림과 보조스트림 방향은 같으나 통로의 사이즈가 다름(1byte / 2byte, 원칙상 결합 불가능)
	// > 기반스트림과 보조스트림 중간에 사이즈를 호환해주는 보조스트림 객체를 붙이면 됨
	// 결론적으로 기반스트림에 보조스트림 2개 붙이겠다.
	//

	// - 출력용스트림: 클라이언트에게 메시지를 전달하기 위한 스트림(서버-->클라이언트)
	// >> 기반스트림: OutputStream 객체 socket.getOutputStream()
	// +
	// + 보조스트림: PrintWriter 객체
	//
	// # 방향은 같으나, 통로 사이즈 다름(원칙상 결합 불가능) 근데 ~ 하도많이~

	// * OutputStreamWriter 객체: 중간에서 스트림 사이즈를 호환해주는 객체
	// 이 객체가 존재하긴 하지만, PrintWriter의 경우 하도 많이 쓰여서
	// 굳이 OutputStreamWriter 객체 필요없이, 바로 1byte짜리 기반스트림과 결합해서 사용가능(특이케이스)
	
	// 7-0단계 스트림을 통해 데이터 읽고 쓰기 (볼일보기): 채팅처럼 지속적으로 메시지가 주고 받아졌으면 좋겠음
	//        > 무한반복문 안에서 읽고 쓰는 구문 작성하기
	// 7-1단계 클라이언트로부터 전달된 메시지 읽어들이기: BufferedReader 객체 사용, 한줄단위 readLine()
	// 7-2단계 반대로 클라이언트에게 전달: PrintWriter 객체 사용, 한줄단위 println()
	// 7-3단계 현재 스트림에 남아있는 데이터를 강제로 내보내는 메소드 호출(출력통로만), flush()
	// 8단계 통신종료절차(자원반납) 생성된 순서의 역순으로 
	// 		정순서: ServerSocket -> Socket -> BufferedReader -> PrintWriter
	// 0단계 위에 생성된 것 null 초기화














