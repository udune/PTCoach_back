package com.fitness.coaching.repository;

import com.fitness.coaching.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 운동 관리를 위한 JPA 리포지토리 인터페이스
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // 운동 이름으로 운동 조회
    Optional<Exercise> findByName(String name);
}