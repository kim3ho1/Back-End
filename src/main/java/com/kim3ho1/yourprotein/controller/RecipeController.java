package com.kim3ho1.yourprotein.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim3ho1.yourprotein.domain.Recipe;
import com.kim3ho1.yourprotein.dto.RecipeResponseDto;
import com.kim3ho1.yourprotein.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe")
public class RecipeController {
	private final RecipeService recipeService;
	// 레시피 가져오기
	@GetMapping("/api")
	public String crawlingRecipe() {
		log.info("=====================");
		recipeService.crawlingRecipe();
		return "ok";
	}

	// 단백질량 기반 레시피 추천
	@GetMapping("/recommend")
	public ResponseEntity<List<RecipeResponseDto.RecipeDetailResponseDto>> searchRecommendedRecipe(@Param("protein") double protein) {
		return ResponseEntity.ok(recipeService.getRecommendedRecipe(protein));
	}

	// 레시피 검색
	@GetMapping("/search")
	public ResponseEntity<List<RecipeResponseDto.RecipeDetailResponseDto>> searchRecipes(@Param("keyword") String keyword) {
		return ResponseEntity.ok(recipeService.searchRecipes(keyword));
	}

	// 레시피 상세 조회
	@GetMapping("/{recipeId}")
	public ResponseEntity<RecipeResponseDto.RecipeDetailResponseDto> getRecipe(@PathVariable("recipeId") Long recipeId) {
		return ResponseEntity.ok(recipeService.getRecipe(recipeId));
	}
}
