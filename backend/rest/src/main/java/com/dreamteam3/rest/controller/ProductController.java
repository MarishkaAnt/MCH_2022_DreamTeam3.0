package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.ProductDto;
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

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    //TODO набросок контроллера, ждем сервисы
    //private final ProductService productService;
    ProductDto productDto = ProductDto.builder()
            .id(1L)
            .name("Test")
            .build();

    @GetMapping
    public List<ProductDto> findByName(@RequestParam String name) {
        return Arrays.asList(productDto);
        //return productService.findByName(name);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productDto;
        //return productService.findById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDTO) {
        return productDto;
        //return productService.save(productDTO);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDTO) {
        return productDto;
        //return productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    public boolean delete(Long id) {
        return true;
        //return productService.delete(id);
    }

}
