package com.faksoy.stocktracking.dto;

import com.faksoy.stocktracking.entity.StoreRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long Id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    @Positive
    private int count;
    private String unit;
    @NotNull
    private StoreRoom storeRoom;
}
