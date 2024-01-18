package taskmanagementsystem.dto.projectdto;

import taskmanagementsystem.model.Project;

public record SimplifiedProjectDto(
        String name,
        Project.Status status
) {}
