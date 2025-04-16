package org.example.repository;

import org.example.model.MentalExercise;
import org.example.model.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentalExerciseRepository extends JpaRepository<MentalExercise, Long> {
    List<MentalExercise> findByCategory(ExerciseCategory category);
}

