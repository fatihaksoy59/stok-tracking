package com.faksoy.stocktracking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Account Login Transfer Object")
public class LoginRequest {
    @ApiModelProperty(required = true,value = "Username")
    private String username;
    @ApiModelProperty(required = true,value = "Password")
    private String password;
}