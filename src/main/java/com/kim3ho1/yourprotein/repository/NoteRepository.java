package com.kim3ho1.yourprotein.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kim3ho1.yourprotein.domain.Note;
import com.kim3ho1.yourprotein.domain.User;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	// @Query(value = "select * note where note.user = :user AND created_at like :today% ; ",nativeQuery = true)
	// List<Note> calculate(@Param("user") User user, @Param("today")LocalDate today);

	@Query(value = "SELECT * FROM note WHERE note.user_id = :userId AND created_at = :today", nativeQuery = true)
	List<Note> calculateToday(@Param("userId") Long userId, @Param("today") String today);

	@Query(value = "select n.created_at, SUM(n.protein) from note n where n.user_id = :userId group by n.created_at order by n.created_at desc limit 7 ; ", nativeQuery = true)
	List<Object []> getWeekly(@Param("userId") Long userId);
}
