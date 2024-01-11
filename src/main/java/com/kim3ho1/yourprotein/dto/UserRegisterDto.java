package com.kim3ho1.yourprotein.dto;

import com.kim3ho1.yourprotein.domain.Gender;
import com.kim3ho1.yourprotein.domain.PurposeType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Data
    @AllArgsConstructor
    @Builder
    public static class UpdateUserRequestDto {
        private double height;
        private double weight;

        public PurposeType purpose;
        public double goalProtein;

    }
    @Data
    @AllArgsConstructor
    @Builder
    public static class UserResponseDto {

        private Long id;
        private String name;
        private String email;
        private Integer age;
        private double height;
        private double weight;
        private Gender gender;
        private PurposeType purpose;
        private double goalProtein;

    }
}
