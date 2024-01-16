package com.example.taskmanagementsystem.dto;

import com.example.taskmanagementsystem.model.Project;
import java.time.LocalDate;

public record ProjectDto(
        Long id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Project.Status status
) {
}
