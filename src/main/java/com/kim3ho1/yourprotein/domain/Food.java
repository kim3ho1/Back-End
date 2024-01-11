package com.kim3ho1.yourprotein.domain;

import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
@Table(name = "food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Long id;

	private String category;
	private String foodName;
	private String amount;
	private String measure;
	private String kcal;
	private String protein;
	private String fat;
	private String carbo;

	@Override
	public String toString() {
		return "음식명: " + foodName + ", 양: " + amount + measure + "칼로리는 " + kcal + ". 단백질: " + protein + "지방: " + fat + "탄수화물: " + carbo + "\n";
	}
}
