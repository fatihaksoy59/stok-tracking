package com.faksoy.stocktracking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Account Register Transfer Object")
public class RegistrationRequest {

    @ApiModelProperty(required = true, value = "nameSurname")
    private String nameSurname;

    @ApiModelProperty(required = true, value = "username")
    private String username;

    @ApiModelProperty(required = true, value = "password")
    private String password;

    @ApiModelProperty(required = true, value = "email")
    private String email;
}