package com.kim3ho1.yourprotein.controller;

import com.kim3ho1.yourprotein.dto.UserRegisterDto;
import com.kim3ho1.yourprotein.service.KakaoService;
import com.kim3ho1.yourprotein.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("")
public class KakaoLoginController {

    @Value("${kakao.client_id}")
    private String client_id;

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private UserService userService;

    private UriComponentsBuilder uriBuilder;

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserRegisterDto.KakaoResponseDto userToken = kakaoService.getAccessTokenFromKakao(client_id, code);
        UserRegisterDto.KakaoUserRegisterDto userInfo = kakaoService.getUserInfo(userToken.getAccessToken());
        if (!userService.isUserByKakaoId(userInfo.getId())) {
            userService.registerKakao(userInfo);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("http://localhost:5500").build().toUri());
        Cookie cookie = new Cookie("accessToken", userToken.getAccessToken());
        response.addCookie(cookie);
        Cookie cookie2 = new Cookie("refreshToken", userToken.getRefreshToken());
        response.addCookie(cookie2);


        return  new ResponseEntity<>(userInfo, headers, 303);
    }
}
