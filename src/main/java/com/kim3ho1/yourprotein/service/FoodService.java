package com.kim3ho1.yourprotein.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kim3ho1.yourprotein.domain.Food;
import com.kim3ho1.yourprotein.domain.Note;
import com.kim3ho1.yourprotein.domain.User;
import com.kim3ho1.yourprotein.dto.NoteResponseDto;
import com.kim3ho1.yourprotein.repository.FoodRepository;
import com.kim3ho1.yourprotein.repository.NoteRepository;
import com.kim3ho1.yourprotein.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodService {
	private final FoodRepository foodRepository;
	private final NoteRepository noteRepository;
	public List<Food> searchFoods(String keyword) {
		List<Food> foods = foodRepository.searchAllByFoodName(keyword);
		return foods;
	}

	public Note noteProtein(double protein) {
		log.info("=========================");
		CustomUserDetails principal = (CustomUserDetails)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		log.info(principal.getUser().getName());
		Note note = Note.builder()
			.user(principal.getUser())
			.protein(protein)
			.build();
		noteRepository.save(note);
		return note;
	}

	double current = 0;
	public NoteResponseDto.NoteStatisticsResponseDto getNoteInfo() {
		CustomUserDetails principal = (CustomUserDetails)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		User user = principal.getUser();

		List<Note> notesByUserAndToday = noteRepository.calculateToday(user.getId(), LocalDate.now().toString());
		notesByUserAndToday.stream().map(data->{current+=data.getProtein(); return null; }).collect(Collectors.toList());

		return new NoteResponseDto.NoteStatisticsResponseDto(user.getGoalProtein(), current);
	}

	// 메인 화면 정보 - 일주일간 섭취량
	public List<NoteResponseDto.WeeklyNoteStatisticsResponseDto> getWeekly() {
		CustomUserDetails principal = (CustomUserDetails)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		User user = principal.getUser();
		List<Object[]> weeklyNotes = noteRepository.getWeekly(user.getId());
		return weeklyNotes.stream().map(data ->{
			return NoteResponseDto.WeeklyNoteStatisticsResponseDto.builder()
				.date(data[0])
				.amount(data[1])
				.build();
		}).collect(Collectors.toList());
	}

	@Transactional
	public HttpStatus deleteNote(Long noteId) {
		CustomUserDetails principal = (CustomUserDetails)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		User user = principal.getUser();
		Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException());
		if (note.getUser() != user)
			return HttpStatus.BAD_REQUEST;
		noteRepository.delete(note);
		return HttpStatus.OK;
	}
}
