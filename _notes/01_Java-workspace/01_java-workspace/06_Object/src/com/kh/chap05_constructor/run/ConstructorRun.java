package com.kh.chap05_constructor.run;

import com.kh.chap05_constructor.model.vo.User;

public class ConstructorRun {

	public static void main(String[] args) {
		
		// User 객체 생성 후 정보 담기
		
		// 1. 기본생성자 + setter 조합 (5번 호출)
		User u1 = new User();
		
		u1.setUserId("user01");
		u1.setUserPwd("pass01");
		u1.setUserName("홍길동");
		u1.setAtge(20);
		u1.setGender('M');
		
		System.out.println(u1.information());
		// 지금까지 배운 내용에 의해
		// 객체를 기본적으로 생성하고 기본값이 들어있는 공간에
		// 내가 원하는 값으로 setter로 채어넣은 뒤 사용한다!
		
		// 그럼 객체생성구문 한 줄만으로(위에 u1.set~ 구문 안쓰고)
		// 처음부터 내가 원하는 값으로 채워진 완성된 객체를 만들고 싶다면?
		// > 생성자 개념 필수!
		
		// 2. 매개변수 3개짜리 생성자
		User u2 = new User("user02", "pass02", "김말똥");
		
		System.out.println(u2.information());
		// > "user02", "pass02", "김말똥", 0, ' '
		// 매개변수 생성자로 객체 생성시
		// 매개변수로 세팅한 필드값에 대해서만 처음부터 해당값이 들어가있음
		
		// 남는 성별, 나이에 대한 값까지 세팅하고 싶다면? setter 호출하기
		u2.setAtge(30);
		u2.setGender('F');
		System.out.println(u2.information());
		
		// 3. 모든 필드에 대한 매개변수 생성자
		User u3 = new User("user03", "pass03", "박말순", 40, 'M');
		
		System.out.println(u3.information());
		// 애초에 모든 필드에 대해 매개변수로 갖는 생성자를 이용해서 객체 생성하면
		// 처음부터 내가 원한는 값으로 채워진 객체를 생성할 수 있다!
		
		// 매개변수 생성자를 잘 이용하면 굳이 setter 메소드는 필요없나?
		// 전혀 아님.
		
		// u3 객체에 대해 비밀번호를 변경해야 할 일이 생긴다면?
		u3.setUserPwd("Password123!");
		System.out.println(u3.information());
		
		// u3 객체에 대해 회원이 이름을 개명했다면?
		u3.setUserName("박개순");
		System.out.println(u3.information());
		
		// 작업 중 일부 필드값만 수정해야 할 일이 자주 발생하기 때문에
		// setter 메소드 또한 무조건 만들어야됨
		
		
	}

}
