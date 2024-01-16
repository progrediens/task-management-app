package com.example.taskmanagementsystem.dto;

import com.example.taskmanagementsystem.model.Project;

public record SimplifiedProjectDto(
        String name,
        Project.Status status
) {}
