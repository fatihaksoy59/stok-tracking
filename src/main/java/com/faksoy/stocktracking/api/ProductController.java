package com.faksoy.stocktracking.api;

import com.faksoy.stocktracking.dto.ProductDto;
import com.faksoy.stocktracking.dto.TokenResponse;
import com.faksoy.stocktracking.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/api/token", description = "Product APIs")

public class ProductController {

    private ProductServiceImpl productServiceImpl;

    @PostMapping
    @ApiOperation(value = "Create Product", response = ProductDto.class)
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto product) {
        return ResponseEntity.ok(productServiceImpl.save(product));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Product By ID", response = ProductDto.class)
    public ResponseEntity<ProductDto> getById(@PathVariable(value = "id", required = true) Long id) {
        log.info("ProductController-> GetByID ");
        log.debug("ProductController-> GetByID -> PARAM:" + id);
        ProductDto productDto = productServiceImpl.getById(id);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/getAll")
    @ApiOperation(value = "Get All Product by Current User", response = ProductDto.class)
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> data = productServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Product", response = ProductDto.class)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody ProductDto product) {
        return ResponseEntity.ok(productServiceImpl.update(id,product));
    }

}
