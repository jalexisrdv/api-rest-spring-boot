package com.jarvcode.order.api.validators;

import com.jarvcode.order.api.entities.Order;
import com.jarvcode.order.api.entities.OrderLine;
import com.jarvcode.order.api.exceptions.ValidateServiceException;

public class OrderValidator {
	
	public static void save(Order order) {
		
		if(order.getLines() == null || order.getLines().isEmpty()) {
			throw new ValidateServiceException("Las líneas son requeridas");
		}
		
		for(OrderLine line: order.getLines()) {
			if(line.getProduct() == null || line.getProduct().getId() == null) {
				throw new ValidateServiceException("El producto es requerido");
			}
			
			if(line.getQuantity() == null){
				throw new ValidateServiceException("La cantidad es requerido");
			}
			if(line.getQuantity() < 0) {
				throw new ValidateServiceException("La cantidad es incorrecto");
			}
		}
	}
}
