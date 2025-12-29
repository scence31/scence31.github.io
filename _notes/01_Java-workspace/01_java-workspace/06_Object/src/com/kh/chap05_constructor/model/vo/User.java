package com.kh.chap05_constructor.model.vo;

// 유저의 정보를 담을 수 있는 VO 클래스
public class User {
	
	// 필드부
	private String userId; // 회원아이디
	private String userPwd; // 회원비번
	private String userName; // 회원명
	private int age; // 나이
	private char gender; // 성별
	
	
	// 생성자부
	// > "생성자"들이 모여있는 "부"분
	
	/*
	 * * 생성자(constructor)
	 * - 이름이 클래스명과 동일하고, 반환형이 없는 일종의 "메소드"같은 놈
	 * - 객체가 생성될 때(new 구문이 실행될 때) 호출되는 메소드임
	 * 
	 * [표현법]
	 * public 클래스명(매개변수 --> 생략가능) {
	 * 
	 * 		실행할코드;
	 * }
	 * 
	 * * 생성자를 작성하는 목적
	 * 1. 객체를 생성하기 위한 목적 --> 기본생성자의 목적
	 * 2. 객체를 생성하는 것 뿐만아니라 
	 *    객체 생성과 동시에 내가 원하는 값으로 필드에 "초기화"할 목적 --> 매개변수생성자의 목적
	 *    
	 * * 생성자 작성시 주의사항
	 * 1. 반드시 이름은 클래스명과 동일해야함
	 * 2. 반환형이 존재하지 않아야함(메소드와 비슷하게 생겨서 주의)
	 * 3. 여러 개 작성이 가능하지만, 매개변수가 중복되면 안됨(이름이 중복되더라도)
	 * 4. 생성자를 아무 것도 작성하지 않을 경우
	 * 	  기본적으로 매개변수가 없는 기본생성자 한 개정도는 JVM이 자동으로 만들어줌!
	 *    > 단, 매개변수가 있는 생성자를 하나라도 명시적으로 작성하게 되면
	 *      JVM이 기본생성자를 자동으로 만들어주지 않음
	 *    > 어찌되었든 간에, 기본생성자는 내가 직접 만들어버릇 하자
	 */
	
	// - 가장 기본적인 형식의 생성자 만들어보기(매개변수 없이)
	// > 기본생성자: 매개변수가 없는 생성자
	public User() {
	
		/*
		 * * 기본생성자
		 * - 단지 객체를 생성하는 목적(heap 영역에 공간 확보)으로 쓰이는 생성자
		 * - 기본생성자로 객체를 생성하면 각 필드에 타입별 기본 값이 담김!
		 * - 기본생성자 작성 자체를 생략하는 경우 오류가 나지 않고,
		 *   호출 또한 제대로 됨 
		 * - 기본생성자를 작성하지 않는 경우 JVM이 자동으로 기본생성자 코드를 만들어서
		 *   실행하기 때문에 항상 사용이 가능했던 것
		 * - 단, 매개변수가 있는 생성자를 하나라도 명시적으로 작성할 경우
		 *   JVM이 기본생성자를 자동으로 만들어주지 않는다!
		 *   (항상 기본생성잔는 직접 만들어버릇 하자)
		 * 
		 */
		
		// System.out.println("잘 호출되나?");
	}
	
	// - 다소 복잡한 형태의 생성자 만들어보기(매개변수 추가)
	// > "매개변수가 있는 생성자"
	public User(String userId, String userPwd, String userName) {
		
		/*
		 * * 매개변수 생성자
		 * - 객체를 생성함과(heap 영역에 공간확보) 동시에
		 *   내가 원하는 값으로 처음부터 초기화할 목적으로 주로 쓰임
		 * - 각 필드에 내가 넣고자하는 ㄱ밧을 "매개변수"롤 전달받아서
		 *   각 필드에 대입하는 코드 위주로 작성한다.(즉 setter메소드의 연장선)
		 * 
		 */
		
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		
	}
	// - 모든 필드에 대해 매개변수로 받는 생성자
	
	public User(String userId, String userPwd, String userName, int age, char gender) {
		/*
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		*/
		// 위와 같이 중복되는 동일한 초기화하는 내용의 코드를 가진 기본생성자가 이미 존재할 경우
		// 해당 생성자를 호출해서 초기화하는 구문을 줄일 수 있다!
		
		// [표현법]
		// this(인자값);
		// > this생성자: 나와 같은 클래스의 생성자를 호출하는 구문
		// 단, this 생성자를 호출할 경우 반드시 생성자 구문 가장 첫 줄에 작성할 것!
		
		this(userId, userPwd, userName);
		// 이 시점 기준으로 userId, userPwd, userName 필드에 초기화가 되어있을 것
		
		this.age = age;
		this.gender = gender;
		// 나머지 필드값들도 초기화 됨
		
		// this(userId, userPwd, userName);
		
		
	}
	
	
	// 메소드부
	// setter 메소드들
	public void setUserId(String userId) {
		
		this.userId = userId;
	}
	
	public void setUserPwd(String userPwd) {
		
		this.userPwd = userPwd;
	}
	
	public void setUserName(String userName) {
		
		this.userName = userName;
	}
	
	public void setAtge(int age) {
		
		this.age = age;
	}
	
	public void setGender(char gender) {
		
		this.gender = gender;
	}
	
	// getter 메소드
	public String getUserId() {
		
		return userId;
	}
	
	public String getUserPwd() {
		
		return userPwd;
	}
	
	public String getUserName() {
		
		return userName;
	}
	
	public int getAge() {
		
		return age;
	}
	
	public char getGender() {
		
		return gender;
	}
	
	// information method
	
	public String information() {
		
		return userId + "\t" + userPwd + "\t" + userName + "\t" + age + "\t" + gender;
		
	}
	
	/*
	 * * 누가 VO클래스를 만들라고 하면..
	 * 필드부(private) --> 메소드부(세터게터, 인포메이션) --> 생성자부(기본생성자)
	 * --> 생성자부(모든 필드에 대한 매개변수 생성자)
	 * 
	 * > 일부 필드에 대한 매개변수 생성자가 필요할 경우
	 *   그 때 가서 만들면 됨
	 */
	
}

