package com.jarvcode.order.api.validators;

import com.jarvcode.order.api.entities.Product;
import com.jarvcode.order.api.exceptions.ValidateServiceException;

public class ProductValidator {
	
	public static Product save(Product product) {
		if(product.getName() == null || product.getName().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(product.getName().length() > 100) {
			throw new ValidateServiceException("El nombre es muy largo (max 100)");
		}
		if(product.getPrice() == null) {
			throw new ValidateServiceException("El precio es requerido");
		}
		if(product.getPrice() < 0) {
			throw new ValidateServiceException("El valor no es valido (min 0)");
		}
		return product;
	}

}
