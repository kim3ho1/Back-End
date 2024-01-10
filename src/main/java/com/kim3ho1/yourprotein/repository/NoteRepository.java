package com.kim3ho1.yourprotein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim3ho1.yourprotein.domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
