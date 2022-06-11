package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Product;
import com.dreamteam3.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product) {
        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setCompany(product.getCompany());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setActive(product.isActive());

        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NullPointerException::new);
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return new ArrayList<Product>(productRepository.findAll());
    }
}
