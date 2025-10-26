package com.fitness.coaching.service;

import com.fitness.coaching.dto.DailyWorkoutLog.Request.DailyWorkoutLogRequest;
import com.fitness.coaching.dto.DailyWorkoutLog.Response.DailyWorkoutLogResponse;
import com.fitness.coaching.entity.DailyWorkoutLog;
import com.fitness.coaching.entity.Exercise;
import com.fitness.coaching.entity.User;
import com.fitness.coaching.entity.WorkoutRecommendation;
import com.fitness.coaching.repository.DailyWorkoutLogRepository;
import com.fitness.coaching.repository.ExerciseRepository;
import com.fitness.coaching.repository.UserRepository;
import com.fitness.coaching.repository.WorkoutRecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 일일 운동 기록 서비스
@Service
@RequiredArgsConstructor
public class DailyWorkoutLogService {

    private final DailyWorkoutLogRepository logRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRecommendationRepository recommendationRepository;

    // 일일 운동 기록 생성
    @Transactional
    public DailyWorkoutLogResponse createLog(DailyWorkoutLogRequest request) {

        // 사용자 존재 여부 확인
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + request.getUserId()));

        // 운동 존재 여부 확인
        Exercise exercise = exerciseRepository.findById(request.getExerciseId())
                .orElseThrow(() -> new IllegalArgumentException("운동을 찾을 수 없습니다. ID: " + request.getExerciseId()));

        // 중복 로그 확인
        logRepository.findByUserIdAndExerciseIdAndWorkoutDate(
                request.getUserId(),
                request.getExerciseId(),
                request.getWorkoutDate()
        ).ifPresent(existingLog -> {
            throw new IllegalArgumentException("해당 날짜에 이미 운동 기록이 존재합니다: " + request.getWorkoutDate());
        });

        // 추천 운동 존재 여부 확인
        WorkoutRecommendation recommendation = null;
        if (request.getRecommendationId() != null) {
            recommendation = recommendationRepository.findById(request.getRecommendationId())
                    .orElseThrow(() -> new IllegalArgumentException("운동 추천을 찾을 수 없습니다. ID: " + request.getRecommendationId()));
        }

        // 일일 운동 기록 생성
        DailyWorkoutLog log = new DailyWorkoutLog();
        log.setUser(user);
        log.setExercise(exercise);
        log.setRecommendation(recommendation);
        log.setSets(request.getSets());
        log.setReps(request.getReps());
        log.setWeight(request.getWeight());
        log.setCompleted(request.getCompleted());
        log.setWorkoutDate(request.getWorkoutDate());

        // 완료된 운동인 경우 완료 시간 설정
        if (request.getCompleted()) {
            log.setCompletedAt(LocalDateTime.now());
        }

        DailyWorkoutLog savedLog = logRepository.save(log);
        return new DailyWorkoutLogResponse(savedLog);
    }

    // 특정 사용자의 일일 운동 기록 조회
    @Transactional(readOnly = true)
    public List<DailyWorkoutLogResponse> getLogsByUserId(Long userId) {
        return logRepository.findByUserId(userId).stream()
                .map(DailyWorkoutLogResponse::new)
                .collect(Collectors.toList());
    }

    // 일일 운동 기록 ID로 조회
    @Transactional(readOnly = true)
    public DailyWorkoutLogResponse getLogById(Long id) {
        DailyWorkoutLog log = logRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("운동 기록을 찾을 수 없습니다. ID: " + id));
        return new DailyWorkoutLogResponse(log);
    }
}