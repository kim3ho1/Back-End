package com.kim3ho1.yourprotein.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PurposeType {
	NORMAL(1,0.8, "NORMAL"), // 운동 안 하는 사람, 콩판 질환 있는 사람
	TRAINING(2,1.2, "TRAINING"), // 운동 하는 사람
	BULK(3,2.0, "BULK"); // 벌크업을 원하는 사람
	private final int index;
	private final Double value;
	private final String role;


	@JsonValue
	public int getIndex() {
		return index;
	}

	public Double getActivity() { return value; }
}
