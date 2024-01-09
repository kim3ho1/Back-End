package com.kim3ho1.yourprotein.security;

import com.kim3ho1.yourprotein.service.KakaoService;
import com.kim3ho1.yourprotein.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    private final UserService userService;

    private final KakaoService kakaoService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {


        try {
            String accessToken = request.getHeader("accessToken");

            userService.deleteRefreshTokenByKakaoId(kakaoService.getUserInfo(accessToken).getId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
