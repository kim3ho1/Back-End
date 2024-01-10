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
}
