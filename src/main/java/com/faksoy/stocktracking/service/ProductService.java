package com.faksoy.stocktracking.service;

import com.faksoy.stocktracking.dto.ProductDto;
import com.faksoy.stocktracking.util.TPage;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto save(ProductDto product);

    ProductDto getById(Long id);

    TPage<ProductDto> getAllPageable(Pageable pageable);

    ProductDto getByProductName(String productName);
}
