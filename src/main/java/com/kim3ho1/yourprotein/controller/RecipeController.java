package com.kim3ho1.yourprotein.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kim3ho1.yourprotein.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe")
public class RecipeController {
	private final RecipeService recipeService;
	@GetMapping("/api")
	public String crawlingRecipe() {
		log.info("=====================");
		recipeService.crawlingRecipe();
		return "ok";
	}
}
