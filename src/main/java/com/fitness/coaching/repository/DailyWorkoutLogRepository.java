package com.fitness.coaching.repository;

import com.fitness.coaching.entity.DailyWorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// 일일 운동 기록을 위한 JPA 리포지토리 인터페이스
@Repository
public interface DailyWorkoutLogRepository extends JpaRepository<DailyWorkoutLog, Long> {

    // 사용자 ID로 일일 운동 기록 조회
    List<DailyWorkoutLog> findByUserId(Long userId);

    // 사용자 ID와 운동 날짜로 일일 운동 기록 조회
    List<DailyWorkoutLog> findByUserIdAndWorkoutDate(Long userId, LocalDate workoutDate);

    // 사용자 ID, 운동 ID, 운동 날짜로 일일 운동 기록 조회
    Optional<DailyWorkoutLog> findByUserIdAndExerciseIdAndWorkoutDate(Long userId, Long exerciseId, LocalDate workoutDate);
}