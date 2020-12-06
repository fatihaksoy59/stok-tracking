package com.faksoy.stocktracking.service;

import com.faksoy.stocktracking.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto product);

    ProductDto getById(Long id);

    List<ProductDto> getAll();
}
