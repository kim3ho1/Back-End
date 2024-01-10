package com.kim3ho1.yourprotein.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {


    MALE(0, "MALE"),
    FEMALE(1, "FEMALE");

    private final Integer value;
    private final String gender;


    @JsonValue
    public String getGender() {
        return gender;
    }

    @JsonValue
    public String getValue() {
        return gender;
    }

    public static Gender getGender(String gender) {

        if (gender.equals("MALE")) {
            return MALE;
        } else if (gender.equals("FEMALE")) {
            return FEMALE;
        }
        throw new RuntimeException();
    }

}
