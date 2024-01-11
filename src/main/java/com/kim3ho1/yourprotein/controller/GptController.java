package com.kim3ho1.yourprotein.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim3ho1.yourprotein.service.GptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gpt")
public class GptController {
	private final GptService gptService;
	@GetMapping("/total")
	public ResponseEntity<String> totalAdvice() {
		return ResponseEntity.ok(gptService.totalAdvice());
	}

	@GetMapping("/plan")
	public ResponseEntity<String> planAdvice() {
		return ResponseEntity.ok(gptService.planAdvice());
	}
}
