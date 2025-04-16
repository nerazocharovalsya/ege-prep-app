package org.example.controller;

import org.example.dto.TrackProgressRequest;
import org.example.dto.MentalProgressDTO;
import org.example.model.MentalExercise;
import org.example.model.MentalProgress;
import org.example.service.MentalHealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.example.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/mental-health")
@RequiredArgsConstructor
public class MentalHealthController {
    private final MentalHealthService mentalHealthService;

    @GetMapping("/exercises")
    public ResponseEntity<List<MentalExercise>> getAllExercises() {
        return ResponseEntity.ok(mentalHealthService.getAllExercises());
    }

    @GetMapping("/exercises/category/{category}")
    public ResponseEntity<List<MentalExercise>> getExercisesByCategory(
            @PathVariable String category
    ) {
        return ResponseEntity.ok(mentalHealthService.getExercisesByCategory(category));
    }

    @GetMapping("/exercise/{id}")
    public ResponseEntity<MentalExercise> getExerciseById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(mentalHealthService.getExerciseById(id));
    }

    @PostMapping("/progress")
    public ResponseEntity<?> trackProgress(
            @Valid @RequestBody MentalProgressDTO progressDTO) {
        try {
            MentalProgress progress = mentalHealthService.trackProgress(progressDTO);
            return ResponseEntity.ok(progress);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/progress")
    public ResponseEntity<List<MentalProgress>> getUserProgress(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        Long userId = Long.parseLong(userDetails.getUsername());
        LocalDateTime start = startDate != null ? LocalDateTime.parse(startDate) : LocalDateTime.now().minusMonths(1);
        LocalDateTime end = endDate != null ? LocalDateTime.parse(endDate) : LocalDateTime.now();

        return ResponseEntity.ok(
                mentalHealthService.getUserProgress(userId, start, end)
        );
    }
}


