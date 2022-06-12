package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

}
