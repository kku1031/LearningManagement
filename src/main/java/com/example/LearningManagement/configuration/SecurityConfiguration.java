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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                        , "/member/find/password" //비밀번호 찾기 페이지
                        , "/member/reset/password" //비밀번호 초기화 페이지


                )
                //모든 접근 허가
                .permitAll();
        
        //관리자 설정
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN");

        //권한없을 때 예외 처리
        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        //로그인
        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .permitAll();
        //로그아웃
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                //세션 초기화
                .invalidateHttpSession(true);

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
