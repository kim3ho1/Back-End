package com.kim3ho1.yourprotein.dto;

import com.kim3ho1.yourprotein.domain.Gender;
import com.kim3ho1.yourprotein.domain.PurposeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class UserRegisterDto {

    @Data
    @AllArgsConstructor
    @Builder
    public static class KakaoUserRegisterDto {

        public Long id;

        public String name;

    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class KakaoResponseDto {

        public String accessToken;

        public String refreshToken;

        public boolean isNewUser;

    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class RegisterRequestDto {

        public String age;
        public Gender gender;
        public String height;
        public String weight;
        public PurposeType purpose;

    }

}
