package com.fitness.coaching.repository;

import com.fitness.coaching.entity.WorkoutRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 운동 추천을 위한 JPA 리포지토리 인터페이스
@Repository
public interface WorkoutRecommendationRepository extends JpaRepository<WorkoutRecommendation, Long> {
}