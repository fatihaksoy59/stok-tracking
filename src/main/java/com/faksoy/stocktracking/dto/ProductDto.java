package com.faksoy.stocktracking.dto;

import com.faksoy.stocktracking.entity.StoreRoom;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @ApiModelProperty(value = "Id")
    private Long Id;
    @NotNull
    @ApiModelProperty(required = true,value = "name")
    private String name;
    @ApiModelProperty(value = "description")
    private String description;
    @NotNull
    @Positive
    @ApiModelProperty(required = true,value = "count")
    private int count;
    @ApiModelProperty(value = "unit")
    private String unit;
    @NotNull
    @ApiModelProperty(required = true,value = "storeRoom")
    private StoreRoom storeRoom;
}
