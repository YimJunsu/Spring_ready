package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅을 위한 어노테이션
@Configuration
public class WebSecurityConfig {
	
	@Autowired AuthenticationFailureHandler authenticationFailureHandler;
	
    // SecurityFilterChain을 사용하여 Spring Security 설정
    // 시큐리티 5.x 이상에서는 WebSecurityConfigurerAdapter를 확장하지 않고, 필터 체인 방식으로 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HttpSecurity 객체를 사용하여 HTTP 요청에 대한 권한을 설정
    	// 시큐리티 5.x 이상에서는 antMatchers 사용 안함
        http.authorizeHttpRequests()
        		.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")  // /member/** 경로는 "USER" 또는 "ADMIN" 역할을 가진 사용자만 접근 허용
        		.requestMatchers("/admin/**").hasRole("ADMIN")  // /admin/** 경로는 "ADMIN" 역할을 가진 사용자만 접근 허용
                .requestMatchers("/**").permitAll()  // 루트 경로("/")는 모두 허용
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()  // 정적 자원(css, js, img)은 모두 허용
                .requestMatchers("/guest/**").permitAll()  // guest 경로는 모두 허용
                .anyRequest().authenticated();  // 그 외 모든 요청은 인증된 사용자만 접근 가능
        // 로그인 폼 설정: 모든 사용자에게 로그인 페이지 접근 허용
        http.formLogin()
        		.loginPage("/loginForm") // default : /login
        		.loginProcessingUrl("/j_spring_security_check") // 시큐리티 인증 url
        		// .failureUrl("/loginError")  // default : /login?error
        		.failureHandler(authenticationFailureHandler)
        		// .defaultSuccessUrl("/")
        		.usernameParameter("j_username") // default : j_username
        		.passwordParameter("j_password") // default : j_password
                .permitAll();  // 로그인 페이지는 모두 허용
        // 로그아웃 설정: 모든 사용자에게 로그아웃 허용
        http.logout()
        	.logoutUrl("/logout") // default
        	.logoutSuccessUrl("/")
        	.permitAll();  // 로그아웃 페이지는 모두 허용
        
        // ssl을 사용하지 않으면 true로
        // CSRF 프로텐션 활성화, 사이트간 요청 위조
        http.csrf()
        	.disable();
        
        return http.build();
    }
//    // 글로벌 인증 설정: 사용자 인증을 메모리 기반으로 설정 -> 스프링 시큐리티 5.X 버전
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // 메모리 기반 인증 설정: 사용자 이름과 비밀번호를 하드코딩하여 설정
//        auth.inMemoryAuthentication()
//        	.passwordEncoder(passwordEncoder())
//            .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")  // "user"라는 사용자, 비밀번호 "1234", "USER" 역할
//            .and()
//            .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");  // "admin"이라는 사용자, 비밀번호 "1234", "ADMIN" 역할
//    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // InMemoryUserDetailsManager는 메모리 기반으로 사용자 정보를 관리하는 클래스 // 스프링 시큐리티 6.X 이상
        // 사용자 정보가 메모리에 저장되며, 인증 시 해당 정보를 이용해 인증을 처리
        return new InMemoryUserDetailsManager(
                // 첫 번째 사용자(user)정의
                User.withUsername("user") // 사용자 이름을 "user"로 설정
                    .password(passwordEncoder().encode("1234")) // 비밀번호를 "1234"로 설정하고, passwordEncoder()를 사용해 암호화
                    .roles("USER") // 이 사용자는 "USER" 역할을 가짐
                    .build(), // User 객체를 빌드하여 InMemoryUserDetailsManager에 추가
                // 두 번째 사용자(admin)정의.
                User.withUsername("admin") // 사용자 이름을 "admin"으로 설정
                    .password(passwordEncoder().encode("1234")) // 비밀번호를 "1234"로 설정하고, 암호화
                    .roles("ADMIN") // 이 사용자는 "ADMIN" 역할을 가짐
                    .build() // User 객체를 빌드하여 InMemoryUserDetailsManager에 추가
        );
    }
    // 비밀번호 인코더
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder는 강력한 해시 알고리즘으로 비밀번호를 암호화
        return new BCryptPasswordEncoder();
    }
}
