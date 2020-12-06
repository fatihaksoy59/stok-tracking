package com.faksoy.stocktracking.service.impl;

import com.faksoy.stocktracking.dto.RegisterResult;
import com.faksoy.stocktracking.dto.RegistrationRequest;
import com.faksoy.stocktracking.dto.UserDto;
import com.faksoy.stocktracking.entity.User;
import com.faksoy.stocktracking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }


    @Transactional
    public RegisterResult register(RegistrationRequest registrationRequest) {
        RegisterResult res = new RegisterResult();
        try {
            checkData(registrationRequest);

            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            user.setCreatedBy(registrationRequest.getUsername());
            user.setCreatedAt(new Date(System.currentTimeMillis()));
            user.setStatus(true);
            userRepository.save(user);

            res.message = "Success";
            res.status = Boolean.TRUE;
            return res;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            res.message = e.getMessage();
            res.status = Boolean.FALSE;
            return res;
        }
    }

    private void checkData(RegistrationRequest registrationRequest) {
        User userCheck = null;
        userCheck = userRepository.findByUsername(registrationRequest.getUsername());
        if (userCheck != null)
            throw new RuntimeException("Bu kullanıcı ile daha once kayıt olunmus");
    }
}