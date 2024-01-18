package taskmanagementsystem.dto;

import taskmanagementsystem.model.Project;

public record SimplifiedProjectDto(
        String name,
        Project.Status status
) {}
