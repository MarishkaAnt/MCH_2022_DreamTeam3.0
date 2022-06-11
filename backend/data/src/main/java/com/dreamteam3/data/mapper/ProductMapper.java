package com.dreamteam3.data.mapper;

import com.dreamteam3.data.dto.ProductDto;
import com.dreamteam3.data.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {
}
