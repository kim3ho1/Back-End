package com.kim3ho1.yourprotein.service;

import com.kim3ho1.yourprotein.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {

    ResponseEntity<?> registerKakao(UserRegisterDto.KakaoUserRegisterDto kakaoUserRegisterDto);

    boolean isUserByKakaoId(Long kakaoId);
}
