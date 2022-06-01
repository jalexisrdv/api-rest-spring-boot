package com.jarvcode.order.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jarvcode.order.api.entities.Product;
import com.jarvcode.order.api.exceptions.GeneralServiceException;
import com.jarvcode.order.api.exceptions.NoDataFoundException;
import com.jarvcode.order.api.exceptions.ValidateServiceException;
import com.jarvcode.order.api.repository.ProductRepository;
import com.jarvcode.order.api.validators.ProductValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
	
	//Se inyecta la instancia creada de ProductRepository en repoProduct
	@Autowired
	private ProductRepository repoProduct;
	
	public Product findById(Long productId) {
		try {
			Product product = repoProduct.findById(productId)
			.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
			return product;
		}catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public void delete(Long productId) {
		try {
			Product product = repoProduct.findById(productId)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));

			repoProduct.delete(product);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public Product save(Product product) {
		try {
			product = ProductValidator.save(product);
			
			if(product.getId() == null) {
				Product newProduct = repoProduct.save(product);
				return newProduct;
			}
			
			Product updateProduct = repoProduct.findById(product.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
			updateProduct.setName(product.getName());
			updateProduct.setPrice(product.getPrice());
			
			return repoProduct.save(updateProduct);
		} catch (NoDataFoundException | ValidateServiceException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public List<Product> findAll(Pageable page) {
		List<Product> products = repoProduct.findAll(page).toList();
		return products;
	}

}
