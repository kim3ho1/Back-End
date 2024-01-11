package com.kim3ho1.yourprotein.controller;

import com.kim3ho1.yourprotein.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kim3ho1.yourprotein.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@PutMapping("/details")
	public ResponseEntity<?> modifyUserDetails(@RequestBody UserRegisterDto.RegisterRequestDto registerRequestDto) {
		userService.modifyUserDetails(registerRequestDto);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/update")
	public ResponseEntity<UserRegisterDto.UserResponseDto> updateUserDetails(@RequestBody UserRegisterDto.UpdateUserRequestDto updateUserRequestDto) {
		return ResponseEntity.ok(userService.updateUserDetails(updateUserRequestDto));
	}

}
