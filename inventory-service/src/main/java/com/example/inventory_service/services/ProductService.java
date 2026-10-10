package com.example.inventory_service.services;

import com.example.inventory_service.entities.Product;
import com.example.inventory_service.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with id: " + id
                ));
    }

    public Product save(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existing = findById(id);

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());

        return productRepository.save(existing);
    }

    public void delete(Long id) {
        Product existing = findById(id);
        productRepository.delete(existing);
    }
}