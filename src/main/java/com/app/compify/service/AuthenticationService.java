package com.app.compify.service;

import com.app.compify.dto.request.SignUpRequest;
import com.app.compify.dto.request.SigninRequest;
import com.app.compify.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
