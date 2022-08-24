package com.sparta.week3_1.security;

import com.sparta.week3_1.repository.RefreshTokenRepository;
import com.sparta.week3_1.security.filter.LoginFilter;
import com.sparta.week3_1.security.jwt.HeaderTokenExtractor;
import com.sparta.week3_1.security.provider.LoginAuthProvider;
import com.sparta.week3_1.security.provider.JWTAuthProvider;
import com.sparta.week3_1.security.filter.JwtAuthFilter;
import com.sparta.week3_1.service.RefreshTokenService;
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

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthProvider jwtAuthProvider;
    private final HeaderTokenExtractor headerTokenExtractor;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public WebSecurityConfig(
            JWTAuthProvider jwtAuthProvider,
            HeaderTokenExtractor headerTokenExtractor,
            RefreshTokenService refreshTokenService,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.jwtAuthProvider = jwtAuthProvider;
        this.headerTokenExtractor = headerTokenExtractor;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(LoginAuthProvider())
                .authenticationProvider(jwtAuthProvider);
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
        //http.csrf()
        //        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        //        .ignoringAntMatchers("/user/**")
        //        // 상품 등록하는 POST요청마다 CSRF 무시
        //        .ignoringAntMatchers("/api/products/**");

        // CSRF protection 비활성화
        http.csrf().disable();


        http.authorizeRequests()
                //.antMatchers("/users/**").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(LoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
        return new LoginSuccessHandler(refreshTokenRepository);
    }

    @Bean
    public LoginAuthProvider LoginAuthProvider() {
        return new LoginAuthProvider(encodePassword());
    }

    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // h2-console 허용
        skipPathList.add("GET,/h2-console/**");
        skipPathList.add("POST,/h2-console/**");

        // 회원 관리 API 허용
        skipPathList.add("POST,/users/signup");
        skipPathList.add("POST,/users/login");

        // 게시글 조회 API 허용
        skipPathList.add("GET,/posts/**");

        // 댓글 조회 API 허용
        skipPathList.add("GET,/comments/**");


        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor,
                refreshTokenService

        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}