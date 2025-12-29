package com.kh.backend.member.model.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(name="Member", description="회원정보를 담는 VO 클래스") // 맨아래 설명 vo
@Alias("member") // 이 Member 클래스에 별칭으로 'member' 붙여줌
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Member {

	@Schema(description="회원 아이디", example="user01")
	private String userId;		//	USER_ID	VARCHAR2(30 BYTE)
	
	@Schema(description="비번", example="xxxx")
	private String userPwd;		//	USER_PWD	VARCHAR2(100 BYTE)
	
	@Schema(description="이름", example="김말똥")
	private String userName;		//	USER_NAME	VARCHAR2(15 BYTE)
	
	@Schema(description="이메일", example="abc@naver.com")
	private String email;		//	EMAIL	VARCHAR2(100 BYTE)
	
	@Schema(description="성별", example="M")
	private String gender;		//	GENDER	VARCHAR2(1 BYTE)
	
	@Schema(description="나이", example="22")
	private int age;				//	AGE	NUMBER
	
	@Schema(description="전번", example="010-1234-1234")
	private String phone;		//	PHONE	VARCHAR2(13 BYTE)
	
	@Schema(description="주소", example="서울")
	private String address;		//	ADDRESS	VARCHAR2(100 BYTE)
	
	@Schema(description="유형", example="관리자")
	private String userRole;		//	USER_ROLE	VARCHAR2(5 BYTE)
	
	@Schema(description="가입일", example="sysdate")
	private Date enrollDate;		//	ENROLL_DATE	DATE
	
	@Schema(description="수정일", example="sysdate")
	private Date modifyDate;		//	MODIFY_DATE	DATE
	
	@Schema(description="회원상태", example="N")
	private String status;		//	STATUS	VARCHAR2(1 BYTE)
}
