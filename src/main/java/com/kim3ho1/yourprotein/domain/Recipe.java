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
@Table(name = "recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private Long id;

	private String recipeName;
	@Column(name = "details", length = 500)
	private String details; // 재료
	private String kcal;
	private String carbo;
	private double protein;
	private String fat;
	private String na;
	private String kind; // 요리 종류
	@Column(name = "images", length = 5000)
	private String images;

	@Column(name = "manual", length = 5000)
	private String manual;

}
