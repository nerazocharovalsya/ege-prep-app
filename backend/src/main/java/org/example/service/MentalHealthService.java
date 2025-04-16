package org.example.service;

import org.example.dto.MentalProgressDTO;
import org.example.model.MentalExercise;
import org.example.model.MentalProgress;
import java.time.LocalDateTime;

import java.util.List;

public interface MentalHealthService {
    List<MentalExercise> getAllExercises();
    MentalExercise getExerciseById(Long id);
    MentalProgress trackProgress(MentalProgressDTO progressDTO);
    List<MentalProgress> getUserProgress(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<MentalExercise> getExercisesByCategory(String category);

}
