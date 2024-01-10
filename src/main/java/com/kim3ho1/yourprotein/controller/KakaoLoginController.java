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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class KakaoLoginController {

    @Value("${kakao.client_id}")
    private String client_id;

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private UserService userService;

    private UriComponentsBuilder uriBuilder;

    @GetMapping("/kakao")
    public ResponseEntity<?> login(@RequestParam("code") String code) throws IOException {
        UserRegisterDto.KakaoResponseDto userToken = kakaoService.getAccessTokenFromKakao(client_id, code);
        UserRegisterDto.KakaoUserRegisterDto userInfo = kakaoService.getUserInfo(userToken.getAccessToken());
        if (!userService.isUserByKakaoId(userInfo.getId())) {
            userService.registerKakao(userInfo);
            userToken.setNewUser(true);
        }
        return ResponseEntity.ok(userToken);
    }
}
