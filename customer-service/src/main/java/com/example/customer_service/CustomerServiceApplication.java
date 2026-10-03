package com.example.customer_service;

import com.example.customer_service.entities.Customer;
import com.example.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CustomerRepository repository) {
		return args -> {

			repository.save(
					new Customer(null, "Houda", "houda@gmail.com")
			);

			repository.save(
					new Customer(null, "Ahmed", "ahmed@gmail.com")
			);

			repository.save(
					new Customer(null, "Sara", "sara@gmail.com")
			);
		};
	}
}