package org.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для отслеживания прогресса ментальных упражнений")
public class MentalProgressDTO {

    @NotNull(message = "User ID cannot be null")
    @Schema(description = "ID пользователя", example = "1")
    private Long userId;

    @NotNull(message = "Exercise ID cannot be null")
    @Schema(description = "ID упражнения", example = "1")
    private Long exerciseId;

    @NotNull(message = "Mood level cannot be null")
    @Min(value = 1, message = "Mood level must be between 1-10")
    @Max(value = 10, message = "Mood level must be between 1-10")
    @Schema(description = "Уровень настроения (1-10)", example = "7")
    private Integer moodLevel;

    @NotNull(message = "Stress level cannot be null")
    @Min(value = 1, message = "Stress level must be between 1-10")
    @Max(value = 10, message = "Stress level must be between 1-10")
    @Schema(description = "Уровень стресса (1-10)", example = "4")
    private Integer stressLevel;

    @Schema(description = "Дополнительные заметки", example = "Чувствую себя лучше после упражнения")
    private String notes;
}