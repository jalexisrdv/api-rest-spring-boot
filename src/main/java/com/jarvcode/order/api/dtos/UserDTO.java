package com.jarvcode.order.api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	
	private Long id;
	private String username;

}
