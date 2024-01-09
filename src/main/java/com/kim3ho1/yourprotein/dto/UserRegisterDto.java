package com.kim3ho1.yourprotein.dto;

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
    @AllArgsConstructor
    @Builder
    public static class KakaoResponseDto {

        public String accessToken;

    }

}
