package com.kim3ho1.yourprotein.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kim3ho1.yourprotein.domain.Food;
import com.kim3ho1.yourprotein.domain.Note;
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
}
