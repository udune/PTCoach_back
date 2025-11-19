package com.fitness.coaching.service;

import com.fitness.coaching.dto.auth.request.LoginRequest;
import com.fitness.coaching.dto.auth.response.TokenResponse;
import com.fitness.coaching.entity.User;
import com.fitness.coaching.repository.UserRepository;
import com.fitness.coaching.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new TokenResponse(token, user.getEmail(), user.getRole().name());
    }
}
