package com.faksoy.stocktracking.service;

import com.faksoy.stocktracking.dto.UserDto;
import com.faksoy.stocktracking.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto user);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);
}