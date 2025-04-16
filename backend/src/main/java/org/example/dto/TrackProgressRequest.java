package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackProgressRequest {
    private Long exerciseId;
    private Integer moodLevel;
    private Integer stressLevel;
    private String notes;
}
