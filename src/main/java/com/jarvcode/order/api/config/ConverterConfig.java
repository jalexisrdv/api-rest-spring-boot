package com.jarvcode.order.api.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jarvcode.order.api.converters.OrderConverter;
import com.jarvcode.order.api.converters.ProductConverter;
import com.jarvcode.order.api.converters.UserConverter;

@Configuration
public class ConverterConfig {
	
	@Value("${config.datetimeformat}")
	private String datetimeFormat;
	
	@Bean
	public ProductConverter getProductConverter() {
		return new ProductConverter();
	}
	
	@Bean
	public OrderConverter getOrderConverter() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(datetimeFormat);
		return new OrderConverter(format, getProductConverter(), getUserConverter());
	}
	
	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}

}
