package com.sparta.week3_1.security;

import com.sparta.week3_1.repository.RefreshTokenRepository;
import com.sparta.week3_1.security.filter.JwtAuthFilter;
import com.sparta.week3_1.security.filter.LoginFilter;
import com.sparta.week3_1.security.provider.JwtProvider;
import com.sparta.week3_1.security.provider.LoginAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(LoginAuthProvider());
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(LoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/users/**", "/posts", "/comments")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public LoginFilter LoginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(authenticationManager());
        loginFilter.setFilterProcessesUrl("/users/login");
        loginFilter.setAuthenticationSuccessHandler(LoginSuccessHandler());
        loginFilter.afterPropertiesSet();
        return loginFilter;
    }

    @Bean
    public LoginSuccessHandler LoginSuccessHandler() {
        return new LoginSuccessHandler(jwtProvider, refreshTokenRepository);
    }

    @Bean
    public LoginAuthProvider LoginAuthProvider() {
        return new LoginAuthProvider(encodePassword());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}