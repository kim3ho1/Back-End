package com.kim3ho1.yourprotein.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String refreshToken;

    @Column(nullable = true)
    private Long kakaoId;

    private double height;
    private double weight;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private PurposeType purpose;

    private double currentProtein;
    private double goalProtein;

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPurpose(PurposeType purpose) {
        this.purpose = purpose;
    }

    public void setGoalProtein(double goalProtein) {
        this.goalProtein = goalProtein;
    }
    // User
    @Override
    public String toString() {
        return "안녕. 나는 " + name + "이야. " + "나는 " + age + "세"+ gender + "이야. 키는" + height + "cm이고 몸무게는 " + weight + "kg이야." + "나는 " + purpose + "를 목적으로 단백질 섭취를 하고 있어.\n";
    }
}
