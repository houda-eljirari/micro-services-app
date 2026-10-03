package com.example.customer_service.services;

import com.example.customer_service.entities.Customer;
import com.example.customer_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer customer) {

        Customer existing = findById(id);

        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());

        return customerRepository.save(existing);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}