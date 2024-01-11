package com.kim3ho1.yourprotein.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kim3ho1.yourprotein.domain.Food;
import com.kim3ho1.yourprotein.domain.Recipe;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	@Query(value = "select * from recipe where protein <= :protein order by protein desc limit 5 ; ", nativeQuery = true)
	List<Recipe> searchAllByProtein(@Param("protein") double protein);

	@Query(value = "select * from recipe where recipe_name like %:keyword% ; ", nativeQuery = true)
	List<Recipe> searchAllByRecipeName(@Param("keyword") String keyword);
}
