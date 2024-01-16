package taskmanagementsystem.dto;

import java.time.LocalDate;
import taskmanagementsystem.model.Project;

public record ProjectDto(
        Long id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Project.Status status
) {
}
