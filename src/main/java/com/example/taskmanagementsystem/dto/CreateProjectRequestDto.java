package com.example.taskmanagementsystem.dto;

import com.example.taskmanagementsystem.model.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateProjectRequestDto(
        @NotBlank(message = "Name can't be null")
        String name,
        String description,
        Project.Status status,
        @NotNull(message = "For better time-management, include the date of project's end.")
        LocalDate endDate
) {
}
