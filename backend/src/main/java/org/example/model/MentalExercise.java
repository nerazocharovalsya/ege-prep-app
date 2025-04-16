package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mental_exercises")
public class MentalExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String instructions;

    @Enumerated(EnumType.STRING)
    private ExerciseCategory category;

    public enum ExerciseCategory {
        BREATHING,
        VISUALIZATION,
        MINDFULNESS,
        AFFIRMATION
    }

    private Integer durationMinutes;
    private String audioGuideUrl;
}


