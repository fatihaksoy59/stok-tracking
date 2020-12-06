package com.faksoy.stocktracking.service.impl;

import com.faksoy.stocktracking.dto.ProductDto;
import com.faksoy.stocktracking.entity.Product;
import com.faksoy.stocktracking.entity.StoreRoom;
import com.faksoy.stocktracking.repository.ProductRepository;
import com.faksoy.stocktracking.repository.StoreRoomRepository;
import com.faksoy.stocktracking.service.ProductService;
import com.faksoy.stocktracking.util.TPage;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRoomRepository storeRoomRepository;
    private final ModelMapper modelMapper;


    @Override
    public ProductDto save(ProductDto product) {

        checkProductData(product);

        Product p = modelMapper.map(product, Product.class);
        //p.setCreatedBy();
        p.setCreatedAt(new Date(System.currentTimeMillis()));
        p.setUpdatedAt(new Date(System.currentTimeMillis()));
        p = productRepository.save(p);
        product.setId(p.getId());
        return product;
    }

    private void checkProductData(ProductDto product) {
        Product person = productRepository.findByName(product.getName());

        StoreRoom storeRoom = storeRoomRepository.getOne(product.getStoreRoom().getId());

        if (person != null)
            throw new IllegalArgumentException("Bu ürün daha önceden oluşturulmuş");

        if (storeRoom == null)
            throw new IllegalArgumentException("Bu id ye sahip bir depo bulunmuyor.");
    }

    @Override
    public ProductDto getById(Long id) {
        Product p = null;
        p = productRepository.getOne(id);
        return modelMapper.map(p, ProductDto.class);
    }

    @Override
    public TPage<ProductDto> getAllPageable(Pageable pageable) {
        Page<Product> data = productRepository.findAll(pageable);
        TPage<ProductDto> response = new TPage<ProductDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProductDto[].class)));
        return response;
    }

    @Override
    public ProductDto getByProductName(String productName) {
        Product p = productRepository.findByName(productName);
        return modelMapper.map(p, ProductDto.class);
    }
}
