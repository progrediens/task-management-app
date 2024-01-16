package com.example.taskmanagementsystem.dto;

import com.example.taskmanagementsystem.model.Project;
import java.time.LocalDate;

public record UpdateProjectRequestDto(
        String name,
        String description,
        LocalDate endDate,
        Project.Status status
) {}
