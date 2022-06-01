package com.jarvcode.order.api.converters;

import com.jarvcode.order.api.dtos.ProductDTO;
import com.jarvcode.order.api.entities.Product;

public class ProductConverter extends AbstractConverter<ProductDTO, Product> {

	@Override
	public Product fromDTO(ProductDTO dto) {
		return Product.builder()
				.id(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.build();
	}

	@Override
	public ProductDTO fromEntity(Product entity) {
		return ProductDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.price(entity.getPrice())
				.build();
	}

}
