package com.ict.pretzel.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ict.pretzel.jwt.JWTUtil;
import com.ict.pretzel.jwt.service.MyUserDetailsService;
import com.ict.pretzel.vo.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// SNS 성공했을때
// OAuth2 로그인 성공시 처리하는 핸들러
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    public OAuth2AuthenticationSuccessHandler(JWTUtil jwtUtil, MyUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        try {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String provider = getProviderFormRequest(request);

            // 성공 후 토큰을 가지고 클라이언트로 넘어간다.
            UserDetails userDetails = userDetailsService.loadUserByOAuth2User(oAuth2User, provider);

            UserVO user = userDetailsService.getUserDetail(userDetails.getUsername());
            if (user.getStatus().equals("0")) {
                // 정지된 계정이면 토큰에 null 반환
                // 로그인 페이지 ( 만약에 로그인 페이지 url 바뀌면 변경 해주기 )
                response.sendRedirect("http://localhost:3000/choiWork?token=");
            }else{
                String token = jwtUtil.generateToken(userDetails);
                response.addHeader("Authorization", "Bearer " + token);
    
                // 로그인 페이지 ( 만약에 로그인 페이지 url 바뀌면 변경 해주기 )
                response.sendRedirect("http://localhost:3000/choiWork?token=" + token);
            }


        } catch (Exception e) {
            e.printStackTrace();
            // 스프링 자체의 로그인 페이지
            response.sendRedirect("/login?error");
        }
    }
    
    public String getProviderFormRequest(HttpServletRequest request){
        String uri = request.getRequestURI();
        if (uri.contains("kakao")) {
            return "kakao";
        }else if (uri.contains("naver")) {
            return "naver";
        }else {
            return "unknown";
        }
    }

}
