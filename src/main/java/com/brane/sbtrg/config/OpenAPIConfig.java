package com.brane.sbtrg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {

		Info info = new Info().title("Product Management API").version("1.0")
				.description("This API exposes endpoints to manage products.")
				.termsOfService("https://www.nslhub.com/terms");

		return new OpenAPI().info(info);
	}
}