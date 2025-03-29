package com.example.demo.auth;

import java.io.IOException;

import javax.security.auth.login.CredentialExpiredException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	// 시큐리티 인증 처리시 사용자 정의 클래스를 사용할 수 있도록 지정, 인증 실패시 재정의한 메서드 호출
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String loginid=request.getParameter("j_username"); // 폼 데이터의 값 변수 저장
		String errormsg="";
		
		if(exception instanceof BadCredentialsException) {
			loginFailCount(loginid);
			errormsg="아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException ) {
			loginFailCount(loginid);
			errormsg="아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof DisabledException) {
			errormsg="계정이 비활성화되었습니다. 관리자에게 문의하세요.";
		} else if (exception instanceof CredentialsExpiredException) {
			errormsg="비밀번호 유효기간이 만료 되었씁니다. 관리자에게 문의하세요.";
		}
		request.setAttribute("username", loginid);
		request.setAttribute("error_message", errormsg);
		
		request.getRequestDispatcher("/loginForm?error=true").forward(request, response);
	}
	// 비밀번호 3번 이상 틀릴시 계정 잠금 처리
	protected void loginFailCount(String username) {
		/*
		 * userDao.countFailure(username); int cnt =
		 * userDao.checkFailureCount(username); if(cnt==3) {
		 *
		 * userDao.disabledUsername(username); }
		 */
	}
	
}
