package taskmanagementsystem.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import taskmanagementsystem.dto.CreateProjectRequestDto;
import taskmanagementsystem.dto.ProjectDto;
import taskmanagementsystem.dto.SimplifiedProjectDto;
import taskmanagementsystem.dto.UpdateProjectRequestDto;

public interface ProjectService {
    ProjectDto createProject(CreateProjectRequestDto project);

    List<SimplifiedProjectDto> getAllProjects(Pageable pageable);

    ProjectDto getProjectDetailsById(Long id);

    ProjectDto updateProjectById(Long id, UpdateProjectRequestDto requestDto);

    void deleteProjectById(Long id);
}
