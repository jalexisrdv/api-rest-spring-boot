package com.jarvcode.order.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jarvcode.order.api.converters.ProductConverter;
import com.jarvcode.order.api.dtos.ProductDTO;
import com.jarvcode.order.api.entities.Product;
import com.jarvcode.order.api.services.ProductService;
import com.jarvcode.order.api.utils.WrapperResponse;

@RestController
public class ProductController {
	
	//Se inyecta la instancia creada de ProductService en serviceProduct
	@Autowired
	private ProductService serviceProduct;
	
	private ProductConverter productConverter = new ProductConverter();
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable("productId") Long productId) {
		Product product = serviceProduct.findById(productId);
		
		ProductDTO dtoProduct = productConverter.fromEntity(product);
		
		return new WrapperResponse(true, "sucess", dtoProduct).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> delete(@PathVariable("productId") Long productId) {
		serviceProduct.delete(productId);
		return new WrapperResponse(true, "sucess", null).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> findAll(
		@RequestParam(value="pageNumber", required=false, defaultValue="0") int pageNumber,
		@RequestParam(value="pageSize", required=false, defaultValue="5") int pageSize
		) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		
		List<Product> products = serviceProduct.findAll(page);
		
		List<ProductDTO> dtoProducts = productConverter.fromEntity(products);
		
		return new WrapperResponse(true, "sucess", dtoProducts).createResponse(HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<ProductDTO> create(@RequestBody Product product) {
		Product newProduct = serviceProduct.save(product);
		
		ProductDTO dtoProduct = productConverter.fromEntity(newProduct);
		
		return new WrapperResponse(true, "sucess", dtoProduct).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping("/products")
	public ResponseEntity<ProductDTO> update(@RequestBody Product product) {
		Product updateProduct = serviceProduct.save(product);
		
		ProductDTO dtoProduct = productConverter.fromEntity(updateProduct);
		
		return new WrapperResponse(true, "sucess", dtoProduct).createResponse(HttpStatus.OK);
	}
	
}
