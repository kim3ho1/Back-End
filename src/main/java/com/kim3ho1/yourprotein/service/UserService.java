package com.kim3ho1.yourprotein.service;

import com.kim3ho1.yourprotein.domain.Note;
import com.kim3ho1.yourprotein.domain.User;
import com.kim3ho1.yourprotein.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    ResponseEntity<?> registerKakao(UserRegisterDto.KakaoUserRegisterDto kakaoUserRegisterDto);

    User findUserByKakao(Long kakaoId);

    boolean isUserByKakaoId(Long kakaoId);

    void deleteRefreshTokenByKakaoId(Long kakaoId);

    void setRefreshTokenByKakaoId(Long kakaoId, String refreshToken);

    void register(UserRegisterDto.RegisterRequestDto registerRequestDto);
}
