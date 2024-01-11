package com.kim3ho1.yourprotein.domain;

import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
@Table(name = "note")
public class Note{
	// 단백질 기록
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "note_id")
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private double protein;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	@PrePersist
	public void createdAt(){
		this.createdAt = LocalDate.now();
		this.modifiedAt = LocalDate.now();
	}
}
