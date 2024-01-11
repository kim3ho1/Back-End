package com.kim3ho1.yourprotein.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kim3ho1.yourprotein.domain.Note;
import com.kim3ho1.yourprotein.domain.User;
import com.kim3ho1.yourprotein.repository.NoteRepository;
import com.kim3ho1.yourprotein.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class GptService {

	private final NoteRepository noteRepository;
	public String totalAdvice() {
		String prompt = "";
		CustomUserDetails principal = (CustomUserDetails)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		User user = principal.getUser();
		// prompt += user.toString();
		// List<Note> notes = noteRepository.calculateToday(user.getId(), LocalDate.now().toString());
		// notes.stream().map(note -> prompt += note.getFood().toString());



		StringBuilder promptBuilder = new StringBuilder();
		promptBuilder.append(user.toString());

		List<Note> notes = noteRepository.calculateToday(user.getId(), LocalDate.now().toString());
		promptBuilder.append("내가 먹은 음식은 아래와 같아. 다양한 영양소 섭취를 위해 어떤 새로운 음식들을 먹으면 좋을지 계획을 짜주고 단백질을 중점으로 내가 먹은 음식은 제외하고 설명해줘.\n\n");
		// Use forEach to append each note's food to the StringBuilder
		notes.forEach(note -> promptBuilder.append(note.getFood().toString()));

		// Convert StringBuilder to String before returning
		return promptBuilder.toString();
	}
	public String planAdvice() {
		String prompt = "";
		CustomUserDetails principal = (CustomUserDetails)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		User user = principal.getUser();
		// prompt += user.toString();
		// List<Note> notes = noteRepository.calculateToday(user.getId(), LocalDate.now().toString());
		// notes.stream().map(note -> prompt += note.getFood().toString());



		StringBuilder promptBuilder = new StringBuilder();
		promptBuilder.append(user.toString());

		promptBuilder.append("나에게 알맞는 식단을 짜줘.");
		return promptBuilder.toString();
	}
}
