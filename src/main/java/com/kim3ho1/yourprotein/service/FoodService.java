package com.kim3ho1.yourprotein.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim3ho1.yourprotein.domain.Food;
import com.kim3ho1.yourprotein.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final FoodRepository foodRepository;
	public List<Food> getFoodInfo(String keyword) {
		List<Food> foods = foodRepository.searchFoodByFoodName(keyword);
		return foods;
	}
}
