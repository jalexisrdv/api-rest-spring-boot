package com.jarvcode.order.api.converters;

import com.jarvcode.order.api.dtos.SignupRequestDTO;
import com.jarvcode.order.api.dtos.UserDTO;
import com.jarvcode.order.api.entities.User;

public class UserConverter extends AbstractConverter<UserDTO, User> {

	@Override
	public User fromDTO(UserDTO dto) {
		if(dto == null) return null;
		return User.builder()
				.id(dto.getId())
				.username(dto.getUsername())
				.build();
	}

	@Override
	public UserDTO fromEntity(User entity) {
		if(entity == null) return null;
		return UserDTO.builder()
				.id(entity.getId())
				.username(entity.getUsername())
				.build();
	}
	
	public User signup(SignupRequestDTO dto) {
		if(dto == null) return null;
		return User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.build();
	}

}
