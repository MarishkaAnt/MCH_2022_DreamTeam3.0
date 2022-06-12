package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.ProductDto;
import com.dreamteam3.data.mapper.ProductMapper;
import com.dreamteam3.data.model.Product;
import com.dreamteam3.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> findByName(@RequestParam String name, @RequestParam int page, @RequestParam int size) {
        List<Product> products = productService.findAllByName(name, page, size);
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<ProductDto> findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(productMapper::toDTO);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDTO) {
        Product product = productService.save(productMapper.toEntity(productDTO));
        return productMapper.toDTO(product);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDTO) {
        Product product = productService.save(productMapper.toEntity(productDTO));
        return productMapper.toDTO(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
