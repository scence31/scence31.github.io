package com.kh.backend.common.interceptor;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.backend.auth.controller.AuthController;
import com.kh.backend.auth.model.service.AuthService;
import com.kh.backend.member.model.vo.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private AuthService authService;

	// 프리핸들 : 요청 전에 제대로 된 사용자인지를 검증해야 하기 때문
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 제대로 된 요청인지에 따라 return true or false
		// 리액트 코드에서 모든요청시 전달값에 JWT 토큰값도 같이 넘길 것
		
		// url 주소로부터 Header 영역에 담긴 인증토큰JWT 값을 뽑기
		String authHeader = request.getHeader("Authorization");
		
		// System.out.println(authHeader);
		// 토큰이 제대로 오면 Bearer eyJh.... or Bearer null
		
		// 토큰값이 제대로 전달됐는지 검사
		if(authHeader != null && !authHeader.equals("") && authHeader.startsWith("Bearer ")) {
			
			// "Bearer " 접두어 없애기
			String jwtTokenString = authHeader.substring(7);
			
			// System.out.println(jwtTokenString); // eyj... or xxxx.. or null
			
			// 1. JWT 발급 시 썼었던 비밀키를 똑같은 형태의 Key 객체로 가공하기
			Key key = Keys.hmacShaKeyFor(AuthController.secretKey.getBytes(StandardCharsets.UTF_8));
			// 전에 암호화했을 때 쓴 키를 복화하할 때도 똑같이 씀(대칭키)
			
			// 2. 위 Key 객체를 가지고 jwtTokenString 문자열 풀어주기(해석, 파싱)
			JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
			
			Claims claims = parser.parseClaimsJws(jwtTokenString).getBody();
			// claims 객체에는 Payload에 담긴 데이터들이 넘어옴
			
			if(!claims.getExpiration().before(new Date())) {
				
				String userId = claims.getSubject();
				
				Member m = authService.loginAdmin(userId);
				
				if(userId != null && m != null && userId.equals(m.getUserId())) {
					
					return true;
				}
			}
			
		}
		
		return false;
	}
	
}
