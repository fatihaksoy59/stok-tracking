package com.faksoy.stocktracking.api;

import com.faksoy.stocktracking.dto.ProductDto;
import com.faksoy.stocktracking.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        log.info("ProductController-> GetByID ");
        log.debug("ProductController-> GetByID -> PARAM:" + id);
        ProductDto productDto = productServiceImpl.getById(id);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> data = productServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody ProductDto product) {
        return ResponseEntity.ok(productServiceImpl.update(id,product));
    }

}
