package com.jarvcode.order.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {
	
	private boolean ok;
	private String message;
	private T body;
	
	public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
		return new ResponseEntity<>(this, status);
	}
	
	public ResponseEntity<WrapperResponse<T>> createResponse() {
		return new ResponseEntity<>(this, HttpStatus.OK);
	}

}
