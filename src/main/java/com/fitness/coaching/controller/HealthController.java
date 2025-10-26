package com.fitness.coaching.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 헬스 체크용 컨트롤러
@Tag(name = "헬스 체크", description = "서버 상태 확인 API")
@RestController
@RequestMapping("/api")
public class HealthController {

    // 헬스 체크 엔드포인트
    @Operation(
            summary = "서버 상태 확인",
            description = "API 서버가 정상적으로 실행 중인지 확인합니다. 인증이 필요 없습니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "서버가 정상적으로 실행 중입니다."
            )
    })
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", "Fitness Coaching API");

        return ResponseEntity.ok(response);
    }
}
