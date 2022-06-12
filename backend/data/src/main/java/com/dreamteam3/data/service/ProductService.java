package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Product;
import com.dreamteam3.data.model.WebPage;
import com.dreamteam3.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final WebPageService webPageService;

    public Product save(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NullPointerException::new);
        productRepository.deleteById(id);
    }

    public List<Product> findAllByName(String name, int pageNumber, int size) {
        Pageable page = PageRequest.of(pageNumber, size);
        List<Product> products = productRepository.findAllByNameContainsIgnoreCase(name, page);
        if (!products.isEmpty()) {
            return products;
        }

        List<WebPage> webPages = webPageService.findAllByTitle(name, pageNumber, size);
        Product.ProductBuilder builder = Product.builder();
        for (WebPage webPage : webPages) {
            Product product = builder
                    .id(webPage.getId())
                    .name(webPage.getTitle())
                    .description(webPage.getUrl())
                    .company(webPage.getCompany())
                    .build();
            products.add(product);
        }
        return products;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
