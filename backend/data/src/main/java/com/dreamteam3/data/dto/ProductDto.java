package com.dreamteam3.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    private CompanyDto company;

}
