package org.example.service;

import org.example.dto.MentalProgressDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.model.MentalExercise;
import org.example.model.MentalProgress;
import org.example.model.User;
import org.example.model.ExerciseCategory;
import org.example.repository.MentalExerciseRepository;
import org.example.repository.MentalProgressRepository;
import org.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentalHealthServiceImpl implements MentalHealthService {

    private final MentalExerciseRepository exerciseRepository;
    private final MentalProgressRepository progressRepository;
    private final UserRepository userRepository;

    @Override
    public List<MentalExercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public MentalExercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));
    }

    @Override
    @Transactional
    public MentalProgress trackProgress(MentalProgressDTO progressDTO) {
        User user = userRepository.findById(progressDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        MentalExercise exercise = exerciseRepository.findById(progressDTO.getExerciseId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));

        return progressRepository.save(
                MentalProgress.builder()
                        .user(user)
                        .exercise(exercise)
                        .completionDate(LocalDateTime.now())
                        .moodLevel(progressDTO.getMoodLevel())
                        .stressLevel(progressDTO.getStressLevel())
                        .notes(progressDTO.getNotes())
                        .build()
        );
    }

    @Override
    public List<MentalProgress> getUserProgress(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return progressRepository.findByUserIdAndCompletionDateBetween(userId, startDate, endDate);
    }

    @Override
    public List<MentalExercise> getExercisesByCategory(String category) {
        try {
            ExerciseCategory categoryEnum = ExerciseCategory.valueOf(category.toUpperCase());
            return exerciseRepository.findByCategory(categoryEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid exercise category: " + category);
        }
    }
}