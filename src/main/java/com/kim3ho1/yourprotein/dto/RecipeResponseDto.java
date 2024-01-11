package com.kim3ho1.yourprotein.dto;

import java.util.List;

import com.kim3ho1.yourprotein.domain.Recipe;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class RecipeResponseDto {
	@Data
	@AllArgsConstructor
	@Builder
	public static class RecipeDetailResponseDto {
		private Long id;

		private String recipeName;
		private String details; // 재료
		private String kcal;
		private String carbo;
		private double protein;
		private String fat;
		private String na;
		private String kind; // 요리 종류
		private List<String> images;

		private List<String> manual;

	}
	public static RecipeDetailResponseDto from(Recipe recipe) {
		return RecipeResponseDto.RecipeDetailResponseDto.builder()
			.id(recipe.getId())
			.recipeName(recipe.getRecipeName())
			.details(recipe.getDetails())
			.kcal(recipe.getKcal())
			.carbo(recipe.getCarbo())
			.protein(recipe.getProtein())
			.fat(recipe.getFat())
			.na(recipe.getNa())
			.kind(recipe.getKind())
			.images(List.of(recipe.getImages().split("\n")))
			.manual(List.of(recipe.getManual().split("\n"))).build();
	}
}
