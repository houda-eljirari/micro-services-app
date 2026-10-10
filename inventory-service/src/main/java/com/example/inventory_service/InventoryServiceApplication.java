package com.example.inventory_service;


import com.example.inventory_service.entities.Product;
import com.example.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository repository) {
		return args -> {
			repository.save(new Product(
					null,
					"Ordinateur portable",
					"Ordinateur portable pour le travail",
					new BigDecimal("7500.00"),
					10
			));

			repository.save(new Product(
					null,
					"Clavier",
					"Clavier USB",
					new BigDecimal("250.00"),
					30
			));

			repository.save(new Product(
					null,
					"Souris",
					"Souris sans fil",
					new BigDecimal("150.00"),
					50
			));
		};
	}
}