package com.kim3ho1.yourprotein.controller;

import com.kim3ho1.yourprotein.gpt.dto.request.GPTCompletionChatRequest;
import com.kim3ho1.yourprotein.gpt.dto.response.CompletionChatResponse;
import com.kim3ho1.yourprotein.gpt.service.GPTChatRestService;
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

	private final GPTChatRestService gptChatRestService;

	@GetMapping("/total")
	public ResponseEntity<CompletionChatResponse> totalAdvice() {
		return ResponseEntity.ok(
				gptChatRestService.completionChat(new GPTCompletionChatRequest("gpt-3.5-turbo", "user", gptService.totalAdvice(), 5000))
		);
	}

	@GetMapping("/plan")
	public ResponseEntity<CompletionChatResponse> planAdvice() {
		return ResponseEntity.ok(
				gptChatRestService.completionChat(new GPTCompletionChatRequest("gpt-3.5-turbo", "user", gptService.planAdvice(), 5000))
		);
	}
}
