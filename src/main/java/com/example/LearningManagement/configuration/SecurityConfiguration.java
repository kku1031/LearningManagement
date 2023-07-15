package com.example.LearningManagement.configuration;

import com.example.LearningManagement.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity //Security 활성화
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //AuthenticationManagerBuilder 기본적으로 회원 정보를 넘겨주는 부분 필요 -> 스프링시큐리티에 알려줘야함
    private final MemberService memberService;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //스프링시큐리티 기본 csrf토큰 해제
        http.csrf().disable();

        http.authorizeRequests()
                //로그인 이전 : 접근 페이지
                .antMatchers(
                        "/"
                        , "/member/register"    //회원가입 페이지
                        , "/member/email-auth"  //회원정보 페이지
                )
                //모든 접근 허가
                .permitAll();
        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .permitAll();

        super.configure(http);
    }

    //AuthenticationManagerBuilder : userDetailsService 설정 필요 -> 클릭 -> UserDetailsService 상속필요.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());
        super.configure(auth);
    }


}
