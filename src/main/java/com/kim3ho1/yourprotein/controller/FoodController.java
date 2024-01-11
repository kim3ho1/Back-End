package com.kim3ho1.yourprotein.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kim3ho1.yourprotein.dto.NoteResponseDto;
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

	@DeleteMapping("/note/{noteId}")
	public ResponseEntity<HttpStatus> deleteNote(@PathVariable("noteId") Long noteId) {
		// TODO Delete 테스트
		return ResponseEntity.ok(foodService.deleteNote(noteId));
	}

	// 메인 화면 정보 - 오늘 섭취량/목표 섭취량
	@GetMapping("")
	public ResponseEntity<NoteResponseDto.NoteStatisticsResponseDto> getNoteInfo() {
		return ResponseEntity.ok(foodService.getNoteInfo());
	}

	// 메인 화면 정보 - 일주일간 섭취량
	@GetMapping("/weekly")
	public ResponseEntity<List<NoteResponseDto.WeeklyNoteStatisticsResponseDto>> getWeekly() {
		return ResponseEntity.ok(foodService.getWeekly());
	}
}
