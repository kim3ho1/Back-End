package com.kim3ho1.yourprotein.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kim3ho1.yourprotein.domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	@Query(value = "select * from food where food_name like %:keyword% ; ", nativeQuery = true)
	List<Food> searchAllByFoodName(@Param("keyword") String keyword);
}
