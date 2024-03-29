package com.kim3ho1.yourprotein.dto;

import com.kim3ho1.yourprotein.domain.Note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class NoteResponseDto {
	@Getter @Setter
	@AllArgsConstructor
	@Builder
	public static class NoteStatisticsResponseDto {

		private double goalProtein;
		private double currentProtein;
	}

	@Getter @Setter
	@AllArgsConstructor
	@Builder
	public static class WeeklyNoteStatisticsResponseDto {

		private Object date;
		private Object amount;
	}

	@Getter @Setter
	@AllArgsConstructor
	@Builder
	public static class NoteRequestDto {

		private Long foodId;
		private double protein;
	}
}
