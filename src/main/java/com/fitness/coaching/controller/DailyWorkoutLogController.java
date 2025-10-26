package com.fitness.coaching.controller;

import com.fitness.coaching.dto.DailyWorkoutLog.Request.DailyWorkoutLogRequest;
import com.fitness.coaching.dto.DailyWorkoutLog.Response.DailyWorkoutLogResponse;
import com.fitness.coaching.service.DailyWorkoutLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 일일 운동 기록 컨트롤러
@Tag(name = "일일 운동 기록", description = "사용자의 일일 운동 기록을 관리하는 API")
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class DailyWorkoutLogController {

    private final DailyWorkoutLogService logService;

    // 일일 운동 기록 생성
    @Operation(
            summary = "운동 기록 생성",
            description = "새로운 일일 운동 기록을 생성합니다. 동일한 사용자, 운동, 날짜의 중복 기록은 생성할 수 없습니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "운동 기록이 성공적으로 생성되었습니다.",
                    content = @Content(schema = @Schema(implementation = DailyWorkoutLogResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청입니다. (필수 값 누락, 중복 기록, 존재하지 않는 사용자/운동 등)"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증이 필요합니다."
            )
    })
    @PostMapping
    public ResponseEntity<DailyWorkoutLogResponse> createLog (
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "생성할 운동 기록 정보",
                    required = true
            )
            @Valid @RequestBody DailyWorkoutLogRequest request
    ) {
        DailyWorkoutLogResponse response = logService.createLog(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 특정 사용자의 일일 운동 기록 조회
    @Operation(
            summary = "사용자별 운동 기록 조회",
            description = "특정 사용자의 모든 운동 기록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "운동 기록 목록을 성공적으로 조회했습니다."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증이 필요합니다."
            )
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DailyWorkoutLogResponse>> getLogsByUserId (
            @Parameter(description = "조회할 사용자 ID", required = true, example = "1")
            @PathVariable Long userId
    ) {
        List<DailyWorkoutLogResponse> logs = logService.getLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    // 일일 운동 기록 ID로 조회
    @Operation(
            summary = "운동 기록 상세 조회",
            description = "운동 기록 ID로 특정 운동 기록의 상세 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "운동 기록을 성공적으로 조회했습니다.",
                    content = @Content(schema = @Schema(implementation = DailyWorkoutLogResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "존재하지 않는 운동 기록입니다."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증이 필요합니다."
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DailyWorkoutLogResponse> getLogById (
            @Parameter(description = "조회할 운동 기록 ID", required = true, example = "1")
            @PathVariable Long id
    ) {
        DailyWorkoutLogResponse log = logService.getLogById(id);
        return ResponseEntity.ok(log);
    }
}