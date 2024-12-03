package com.example.todolist.security.config;

import com.example.todolist.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authService)  // AuthService를 통해 사용자 정보 로드
                .passwordEncoder(passwordEncoder())  // 비밀번호 인코딩 설정
                .and()
                .build();  // AuthenticationManager 반환
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/todo/member/*", "/todo/board/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/todo/member/signIn") // 사용자 정의 로그인 페이지
                .loginProcessingUrl("/todo/member/signIn") // 로그인 처리 URL
                .defaultSuccessUrl("/",true)
                .and()
                .logout()
                .invalidateHttpSession(true) // 세션 무효화
                .clearAuthentication(true) // 인증 정보 제거
                .logoutUrl("/todo/member/signOut")
                .logoutSuccessUrl("/")
                .and()
                .cors();

        return http.build();
    }
}
