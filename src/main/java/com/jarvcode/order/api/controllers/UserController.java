package com.jarvcode.order.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jarvcode.order.api.converters.UserConverter;
import com.jarvcode.order.api.dtos.LoginRequestDTO;
import com.jarvcode.order.api.dtos.LoginResponseDTO;
import com.jarvcode.order.api.dtos.SignupRequestDTO;
import com.jarvcode.order.api.dtos.UserDTO;
import com.jarvcode.order.api.entities.User;
import com.jarvcode.order.api.services.UserService;
import com.jarvcode.order.api.utils.WrapperResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService serviceUser;
	@Autowired
	private UserConverter coverterUser;
	
	@PostMapping("/signup")
	public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request) {
		User user = serviceUser.createUser(coverterUser.signup(request));
		return new WrapperResponse<>(true, "sucess", coverterUser.fromEntity(user))
				.createResponse();
	}
	
	@GetMapping("/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
		LoginResponseDTO response = serviceUser.login(request);
		return new WrapperResponse<>(true, "sucess", response)
				.createResponse();
	}

}
