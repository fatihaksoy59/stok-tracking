package com.faksoy.stocktracking.api;

import com.faksoy.stocktracking.dto.LoginRequest;
import com.faksoy.stocktracking.dto.RegisterResult;
import com.faksoy.stocktracking.dto.RegistrationRequest;
import com.faksoy.stocktracking.dto.TokenResponse;
import com.faksoy.stocktracking.entity.User;
import com.faksoy.stocktracking.repository.UserRepository;
import com.faksoy.stocktracking.security.JwtTokenUtil;
import com.faksoy.stocktracking.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
@Api(value = "/api/token", description = "Account APIs")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Login User", response = TokenResponse.class)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "Register User", response = RegisterResult.class)
    public ResponseEntity<RegisterResult> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        RegisterResult response = userService.register(registrationRequest);
        return ResponseEntity.ok(response);
    }

}