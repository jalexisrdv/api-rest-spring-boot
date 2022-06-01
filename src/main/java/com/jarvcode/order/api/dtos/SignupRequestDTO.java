package com.jarvcode.order.api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDTO {
	
	private String username;
	private String password;

}
