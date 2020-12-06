package com.faksoy.stocktracking.service.impl;

import com.faksoy.stocktracking.dto.ProductDto;
import com.faksoy.stocktracking.entity.Product;
import com.faksoy.stocktracking.entity.StoreRoom;
import com.faksoy.stocktracking.repository.ProductRepository;
import com.faksoy.stocktracking.repository.StoreRoomRepository;
import com.faksoy.stocktracking.repository.UserRepository;
import com.faksoy.stocktracking.service.ProductService;
import com.faksoy.stocktracking.util.UserUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRoomRepository storeRoomRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public ProductDto save(ProductDto product) {

        checkProductData(product);

        Product p = modelMapper.map(product, Product.class);

        p = setLocalDatas(p);

        p = productRepository.save(p);

        product.setId(p.getId());
        return product;
    }

    private void checkProductData(ProductDto product) {
        Product person = productRepository.findByName(product.getName());

        if (person != null)
            throw new IllegalArgumentException("Bu ürün daha önceden oluşturulmuş");

        try {
            StoreRoom storeRoom = storeRoomRepository.findById(product.getStoreRoom().getId()).get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Bu id ye sahip bir depo bulunmuyor.");
        }

    }

    private Product setLocalDatas(Product p) {
        User userForSession =UserUtils.getUser();

        p.setCreatedAt(new Date(System.currentTimeMillis()));
        p.setCreatedBy(userForSession.getUsername());
        p.setStatus(true);
        p.setUser(userRepository.findByUsername(userForSession.getUsername()));

        return p;
    }

    @Override
    public ProductDto getById(Long id) {
        Product p = null;
        p = productRepository.findById(id).get();
        return modelMapper.map(p, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll() {
        com.faksoy.stocktracking.entity.User user= userRepository.findByUsername(UserUtils.getUser().getUsername());

        List<Product> data = productRepository.findByUser(user);
        return Arrays.asList(modelMapper.map(data, ProductDto[].class));
    }

    public ProductDto update(Long id, ProductDto productDto) {
        Product productDB = productRepository.getOne(id);

        User userForSession =UserUtils.getUser();

        productDB.setDescription(productDto.getDescription());
        productDB.setCount(productDto.getCount());
        productDB.setStoreRoom(productDto.getStoreRoom());
        productDB.setUpdatedAt(new Date(System.currentTimeMillis()));
        productDB.setUpdatedBy(userForSession.getUsername());
        productRepository.save(productDB);
        return modelMapper.map(productDB,ProductDto.class);
    }

}
