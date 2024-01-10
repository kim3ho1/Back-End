package com.kim3ho1.yourprotein.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kim3ho1.yourprotein.service.FoodService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/food")
public class FoodController {
	private final FoodService foodService;

	// 음식 검색
	@GetMapping("/search")
	public ResponseEntity searchFoods(@RequestParam("keyword") String keyword) {
		return ResponseEntity.ok(foodService.searchFoods(keyword));
	}

	// 노트 기록
	@PostMapping("/note")
	public ResponseEntity<String> noteProtein(@RequestBody double protein) {
		foodService.noteProtein(protein);
		return ResponseEntity.ok("ok");
	}
}
