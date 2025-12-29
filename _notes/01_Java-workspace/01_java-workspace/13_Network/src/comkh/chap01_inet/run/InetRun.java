package comkh.chap01_inet.run;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetRun {

	/*
	 * * 네트워크 (Network = Net + work)
	 * - 여러 대의 컴퓨터들이 서로 연결되어있는 통신망을 일컫는 용어
	 * 	 (여러 대의 컴퓨터들이 연결되서 그물망 모영을 이루고 있다)
	 * - 네트워크를 통해 컴퓨터들은 서로간의 데이터를 주고 받을 수 있다
	 * 
	 * * IP 주소 와 Port 번호
	 * - IP 주소 : 네트워크 상의 각 컴퓨터들을 식별/구분 해줄 구 있는 번호 (주소)
	 * 			  컴퓨터의 주민등록번호 같은 개념
	 * - Port 번호 : 컴퓨터 안에서 작동하는 여러 개의 프로그램 중에
	 * 				하나를 찾을 때 , 각각의 프로그램을 식별/구분 해주는 내부 주소
	 * > IP주소 같은 네트워크 범위 안에서 + Port 번호는 같은 컴퓨터 내부에서 중복 x
	 * 	 IP 주소는 Port 번호든 간에 해당 범위 내에서 중복이 일어나면 충돌 (conflict) 이 일어남
	 * > IP 주소, Port 번호는 고정값이 아닌 변동이 가능한 유동적인 값
	 * 
	 * * 서버와 클라이언트
	 * - 서버 : 클라이언트 (고객) 에게 서비스를 제공해주는 PC or 프로그램
	 *		   클라이언트의 요청을 처리해서 등답 해주는 웨이터 역할
	 * - 클라이언트 : 서버에 요청하는 PC or 프로구램
	 * 				서비스를 제공받는 고객
	 * 
	 * 					1. 요청
	 * 		클라이언트 --------------> 서버
	 * 								 └ 2. 요청받은 내용을 처리
	 * 				<--------------
	 * 					3. 응답 
	 * 
	 * > 클라이언트 쪽에서 서버로 요청을 보내면 위해서는
	 * 	 요청하고자 하는 서버의 IP 주소와 Port 번호를 알고 있어야 한다
	 * 	 (이 세상에는 수많은 서버 컴퓨터들이 존재하기 때문)
	 * 
	 *  * InetAdderess 클래스
	 *  - 자바에서 IP 주소 관련된 (네트워크 관련된) 것들을 다루기 위해 사용되는 클래래스
	 *  - java.net 패키지에 정의되어 있음 (java.net.InetAdderess)
	 *  
	 */
	
	
	
	public static void main(String[] args) {
		
		
		try {
			// InetAddress 객체 얻어내기
			InetAddress localhost = InetAddress.getLocalHost();
			//> 평소대로 new 생성자 구문을 통해서가 아닌
			//  InetAddress 클래스의 static 메소드인 get.LocalHost() 메소드를 이용하여 객체 얻어내기
			//> getLocalHost() : PC 에 대한 정보를 InetAddress 타입의 객체로 생성해서 반환
			//> localHost : 지역호스트 - 네트워크 상에서 내 pc를 지칭하는 용어
			
			System.out.println(localhost/* .toString */);
			//> 내PC명 + "/" + 내IP주소
			
			// 내PC명 따로, 내IP주소 따로 String 으로 분리해서 보고 싶음
			//String [] arr = localhost.toString().split("/");
			//System.out.println("내 pc명: "+ arr[0]);
			//System.out.println("내 IP주소: "+ arr[1]);
			//> 위와 같이 내가 직접 분리해서 알아낼 수 있지만, 처음부터 따로따로 바로 얻어낼 수도 있다
			
			System.out.println("내 pc명: "+ localhost.getHostName()); // 내 pc명을 문자열로 리턴
			System.out.println("내 IP명: "+ localhost.getHostAddress()); // 내 ip주소를 문자열로 리턴
						
			//> InetAddress 객체에 내 PC 에 대한 정보를 담아서 확인
			
			System.out.println("----------------------");
			
			// 내가 알고있는 도메인 주소 를 통해서 그 서버와 관련된 정보를 얻기
			//> 도메인 주소: 사용자가 웹 사이트에 접속할 때 (헤딩 웹 서버로 접속시켜달라고 요청 보낼 때)
			//	      	  원칙 상 IP 주소와 Port 번호를 대고 접속하는게 맞지만,
			//			  매번 그 IP 주소 외우기 어렵기 때문에
			//			  외우기 쉬운 구조로 사용자에게 제공해주는 주소
			
			//> 주소창에 도메인 주소를 쳤을 때 일어나는 일
			//	도메인 주소 입력 -->  DNS --> 해당 서버
 			
			// DNS(Domain Name Server) : 도메인 주소 + IP 주소들이 1:1 로 맵핑되어있는 서버
			
			InetAddress googleHost = InetAddress.getByName("www.google.com");
			//> getByName("도메인주소") : 도메인 주소라는 고유한 주소를 통해서
			//							해상 서버의 정보를 얻어내서 InetAddress 객체로 반환
			
			System.out.println(googleHost);
			System.out.println("구글 서버:"+ googleHost.getHostName());
			System.out.println("구글 ip:"+ googleHost.getHostAddress());
			
			//> 사람들이 많이 사용하는 상용 웹사이트들의 서버는 대부분 여러대임
			// 	분산 구조로 이루어져 있다
			//> 요청이 한대의 서버로 몰리면 해당 서버에 과부하가 걸리면서 뻗기 떄문
			//  예) 수간친청, 티켓팅 등... -> DDos 공격의 원리
			
			System.out.println("----------------------");
	
			// 도메인 주소를 통해서 그 서버 "들" 에 대한 정보를 한번에 얻어내보기
			InetAddress[] naverHost = InetAddress.getAllByName("www.naver.com");
			
			System.out.println("네이버 서버 댓수: "+ naverHost.length);
			
			/*
			for( int i=0; i<naverHost.length; i++) {
				
				//System.out.println(naverHost[i]);
				System.out.println(naverHost[i].getHostAddress());
				
			}
			*/
			
			// 향상된 for 문 (for each 문)
			//> 배열의 경우에는 일반 for 문 대신 향상된 for 문 또한 사용가능
			//	for(변수선언문 : 배열명) {}
			//> 헤당 배열의 0번 인덱스~ 마지막 인덱스 까지 순차적으로 모두 다 접근 해줌			
			for(InetAddress n :naverHost) {
				//System.out.println(n);
				System.out.println(n.getHostAddress());
			}
			
			//> InetAddress 객체에 잘 알려진 다른 PC 에 대한 정보를 담아서 확인
			
			
			} catch(UnknownHostException e) {
				e.printStackTrace();
				
			}
			
		
	}
	
	/*
	 * * 현재 구동중인 서버가 있다면, 클라이언트는 항상 그 서버로 요청 보낼수 있음
	 * > 서버라는 그 요청을 받아서 처리하고
	 * > 처리한 결과를 클라이언트에게 응답 으로 돌려주는 구조임
	 * 
	 * * 요청 과 응답 에 의해서 프로그램은 항상 돌아감(통신 한다 라고 표현)
	 * > 웹에서의 통신 방식: HTTP 프로토콜 통신 (HTTPS: 보안 절차가 추가된 버전)
	 * 
	 * * 자바만을 가지고 서버와 클라이언트 간의 간단한 통신 만들기
	 * > 이 때 데이터를 주고받고(입출력) 하려면 서버와 클라이언트 간에 스트림(연결통로)을 만들 수밖에 없음
	 * 
	 * 프로그램(기준점) ---> 외부매체(파일): 단순 스트림 만으로 구현 가능
	 *             <--- 
	 * 
	 * 
	 * 프로그램(기준점) || ---> ||프로그램(기준점)
	 * 프로그램(기준점) || <--- ||프로그램(기준점) 
	 * > 스트림 연결을 위한 대문 역할이 더 필요함
	 * 소켓(Socket): 스트림을 연결하기 위한 문
	 * 프로세스(== 프로그램이 메모리에서 실행중인 상태) 간 통신을 담당하는 대문 역할
	 * 
	 * 소켓 프로그래밍: TCP / UDP 방식이 있음
	 * - TCP 방식: 데이터 전송 속도는 느리지만 정확하고 안정적으로 전달 가능
	 *   주로 신뢰성이 요구되는 프로그램에서 사용(웹, 이메일, 파일전송 등)
	 * 
	 * - UDP 방식: 속도는 빠르지만 신뢰성이 없는 데이터가 전송될 수 있음
	 *   주로 데이터를 단순히 빠른 속도로 전송하는 프로그램에서 사용(실시간 스트리밍 등)
	 * 
	 * 
	 * 
	 * 
	 */
	
}