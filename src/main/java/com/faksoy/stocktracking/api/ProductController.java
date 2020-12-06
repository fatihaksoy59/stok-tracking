package com.faksoy.stocktracking.api;

import com.faksoy.stocktracking.dto.ProductDto;
import com.faksoy.stocktracking.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private ProductServiceImpl productServiceImpl;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto product) {
        return ResponseEntity.ok(productServiceImpl.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(value = "id", required = true) Long id) {
        log.info("ProjectController-> GetByID ");
        log.debug("ProjectController-> GetByID -> PARAM:" + id);
        ProductDto projectDto = productServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }
}
