package com.fitness.coaching.controller;

import com.fitness.coaching.dto.auth.request.LoginRequest;
import com.fitness.coaching.dto.auth.response.TokenResponse;
import com.fitness.coaching.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "사용자 인증 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "사용자 로그인",
            description = "이메일과 비밀번호로 사용자를 인증하고 JWT 토큰을 발급합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 인증되었습니다. JWT 토큰이 발급됩니다."
    )
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        TokenResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
