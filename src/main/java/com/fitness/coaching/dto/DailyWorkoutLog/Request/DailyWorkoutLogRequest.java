package com.fitness.coaching.dto.DailyWorkoutLog.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

// 일일 운동 기록 생성 요청 DTO
@Schema(description = "일일 운동 기록 생성 요청")
@Data
public class DailyWorkoutLogRequest {

    // 사용자 ID
    @Schema(description = "사용자 ID", example = "1", required = true)
    @NotNull(message = "사용자 ID는 필수입니다")
    private Long userId;

    // 운동 ID
    @Schema(description = "운동 ID", example = "1", required = true)
    @NotNull(message = "운동 ID는 필수입니다")
    private Long exerciseId;

    // 운동 추천 ID
    @Schema(description = "운동 추천 ID (선택 사항)", example = "1")
    private Long recommendationId;

    // 세트 수
    @Schema(description = "세트 수", example = "3", required = true)
    @NotNull(message = "세트 수는 필수입니다")
    @Positive(message = "세트 수는 양수여야 합니다")
    private Integer sets;

    // 반복 횟수
    @Schema(description = "반복 횟수 (회)", example = "10", required = true)
    @NotNull(message = "반복 횟수는 필수입니다")
    @Positive(message = "반복 횟수는 양수여야 합니다")
    private Integer reps;

    // 중량 (kg)
    @Schema(description = "중량 (kg)", example = "50.5")
    private Double weight;

    // 완료 여부
    @Schema(description = "운동 완료 여부", example = "true", required = true)
    @NotNull(message = "완료 여부는 필수입니다")
    private Boolean completed;

    // 운동 날짜
    @Schema(description = "운동 날짜", example = "2025-10-26", required = true)
    @NotNull(message = "운동 날짜는 필수입니다")
    private LocalDate workoutDate;
}