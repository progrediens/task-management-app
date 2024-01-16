package taskmanagementsystem.dto;

import java.time.LocalDate;
import taskmanagementsystem.model.Project;

public record UpdateProjectRequestDto(
        String name,
        String description,
        LocalDate endDate,
        Project.Status status
) {}
