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
@CrossOrigin("*")
public class UserController {
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(UserRegisterDto.RegisterRequestDto registerRequestDto) {
		userService.register(registerRequestDto);
		return ResponseEntity.ok(null);
	}

}
