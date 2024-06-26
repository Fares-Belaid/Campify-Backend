package com.app.compify.service.impl;

import com.app.compify.dao.entity.User;
import com.app.compify.dao.repository.UserRepository;
import com.app.compify.service.AuthenticationService;
import com.app.compify.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.compify.dto.request.SignUpRequest;
import com.app.compify.dto.request.SigninRequest;
import com.app.compify.dto.response.JwtAuthenticationResponse;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())){
            var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                    .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole()).build();
            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        }else throw new IllegalArgumentException("email already exist");

    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}