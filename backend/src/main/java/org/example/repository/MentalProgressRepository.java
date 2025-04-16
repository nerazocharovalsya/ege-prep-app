package org.example.repository;

import org.example.model.MentalProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MentalProgressRepository extends JpaRepository<MentalProgress, Long> {
    List<MentalProgress> findByUserIdAndCompletionDateBetween(
            Long userId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}