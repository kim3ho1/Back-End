package com.kim3ho1.yourprotein.filter;

import com.kim3ho1.yourprotein.domain.User;
import com.kim3ho1.yourprotein.dto.UserRegisterDto;
import com.kim3ho1.yourprotein.security.CustomUserDetails;
import com.kim3ho1.yourprotein.service.KakaoService;
import com.kim3ho1.yourprotein.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@RequiredArgsConstructor
public class KakaoTokenFilter extends OncePerRequestFilter {

    private KakaoService kakaoService;
    private UserService userService;

    public KakaoTokenFilter(KakaoService kakaoService, UserService userService) {
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    @Value("${kakao.client_id}")
    private String client_id;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException
            //,CustomNoTokenException, MalformedJwtException
    {

        try {
            log.info("Kakao Token Filter Activated");
            String bearerToken = request.getHeader("Authorization");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                log.info("header : " + headerNames.nextElement());
            }
            log.info("Token : "+ bearerToken);

            // accessToken 없이 접근할 경우
            if (bearerToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            authenticateAccessToken(bearerToken.substring(7));

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticateAccessToken(String accessToken) {

        try {
        //Get kakao Id
        UserRegisterDto.KakaoUserRegisterDto userInfo = kakaoService.getUserInfo(accessToken);

        // userEntity를 생성하여 값 set
        User user = userService.findUserByKakao(userInfo.getId());

        // UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        log.info("Authorization : Kakao id --> {}", userInfo.getId());
        log.info("Authorization : Name --> {}", userInfo.getName());


        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                customUserDetails,
                null,
                customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //TODO : Refresh Token 작업
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
