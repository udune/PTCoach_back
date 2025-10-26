package com.fitness.coaching.dto.DailyWorkoutLog.Response;

import com.fitness.coaching.entity.DailyWorkoutLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 일일 운동 기록 응답 DTO
@Schema(description = "일일 운동 기록 응답")
@Data
public class DailyWorkoutLogResponse {

    // 운동 기록 ID
    @Schema(description = "운동 기록 ID", example = "1")
    private Long id;

    // 사용자 ID
    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    // 사용자 이름
    @Schema(description = "사용자 이름", example = "홍길동")
    private String userName;

    // 운동 ID
    @Schema(description = "운동 ID", example = "1")
    private Long exerciseId;

    // 운동 이름
    @Schema(description = "운동 이름", example = "벤치 프레스")
    private String exerciseName;

    // 운동 추천 ID
    @Schema(description = "운동 추천 ID", example = "1")
    private Long recommendationId;

    // 세트 수
    @Schema(description = "세트 수", example = "3")
    private Integer sets;

    // 반복 횟수
    @Schema(description = "반복 횟수 (회)", example = "10")
    private Integer reps;

    // 중량 (kg)
    @Schema(description = "중량 (kg)", example = "50.5")
    private Double weight;

    // 완료 여부
    @Schema(description = "운동 완료 여부", example = "true")
    private Boolean completed;

    // 운동 날짜
    @Schema(description = "운동 날짜", example = "2025-10-26")
    private LocalDate workoutDate;

    // 완료 시간
    @Schema(description = "운동 완료 시간", example = "2025-10-26T14:30:00")
    private LocalDateTime completedAt;

    // 생성 시간
    @Schema(description = "기록 생성 시간", example = "2025-10-26T14:30:00")
    private LocalDateTime createdAt;

    // Entity를 DTO로 변환하는 생성자
    public DailyWorkoutLogResponse(DailyWorkoutLog log) {
        this.id = log.getId();
        this.userId = log.getUser().getId();
        this.userName = log.getUser().getName();
        this.exerciseId = log.getExercise().getId();
        this.exerciseName = log.getExercise().getName();
        this.recommendationId = log.getRecommendation() != null ? log.getRecommendation().getId() : null;
        this.sets = log.getSets();
        this.reps = log.getReps();
        this.weight = log.getWeight();
        this.completed = log.getCompleted();
        this.workoutDate = log.getWorkoutDate();
        this.completedAt = log.getCompletedAt();
        this.createdAt = log.getCreatedAt();
    }
}