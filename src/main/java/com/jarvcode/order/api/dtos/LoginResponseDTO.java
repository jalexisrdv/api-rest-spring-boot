package com.jarvcode.order.api.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
	
	private UserDTO user;
	private String token;

}
