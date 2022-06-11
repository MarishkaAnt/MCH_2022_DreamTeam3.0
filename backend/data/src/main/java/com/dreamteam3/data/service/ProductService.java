package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Product;
import com.dreamteam3.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NullPointerException::new);
        productRepository.deleteById(id);
    }

    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
