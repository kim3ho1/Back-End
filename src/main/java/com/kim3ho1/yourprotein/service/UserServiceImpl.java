package com.kim3ho1.yourprotein.service;

import com.kim3ho1.yourprotein.domain.User;
import com.kim3ho1.yourprotein.dto.UserRegisterDto;
import com.kim3ho1.yourprotein.repository.UserRepository;
import com.kim3ho1.yourprotein.security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> registerKakao(UserRegisterDto.KakaoUserRegisterDto kakaoUserRegisterDto) {

        log.info("User service : Kakao User Register id --> {}", kakaoUserRegisterDto.getId());
        log.info("User service : Kakao User Register name --> {}", kakaoUserRegisterDto.getName());

        userRepository.save(User.builder()
                .kakaoId(kakaoUserRegisterDto.getId())
                .email("")
                .name(kakaoUserRegisterDto.getName())
                .build()
        );

        return null;
    }

    @Override
    public User findUserByKakao(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId).orElseThrow();
    }

    @Override
    public boolean isUserByKakaoId(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId).isPresent();
    }

    @Override
    public void deleteRefreshTokenByKakaoId(Long kakaoId) {
        User user =  userRepository.findByKakaoId(kakaoId).orElseThrow();
    }

    @Override
    public void setRefreshTokenByKakaoId(Long kakaoId, String refreshToken) {
        User user =  userRepository.findByKakaoId(kakaoId).orElseThrow();
        user.setRefreshToken(refreshToken);
    }

    @Override
    public void register(UserRegisterDto.RegisterRequestDto registerRequestDto) {
        log.info("modify user details");
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext();
        User user = userDetails.getUser();

        user.setAge(Integer.valueOf(registerRequestDto.getAge()));
        user.setGender(registerRequestDto.getGender());
        user.setHeight(Double.parseDouble(registerRequestDto.height));
        user.setPurpose(registerRequestDto.getPurpose());

        userRepository.save(user);

    }
}
