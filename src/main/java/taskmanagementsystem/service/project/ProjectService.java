package taskmanagementsystem.service.project;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import taskmanagementsystem.dto.projectdto.CreateProjectRequestDto;
import taskmanagementsystem.dto.projectdto.ProjectDto;
import taskmanagementsystem.dto.projectdto.SimplifiedProjectDto;
import taskmanagementsystem.dto.projectdto.UpdateProjectRequestDto;

public interface ProjectService {
    ProjectDto createProject(CreateProjectRequestDto project, Authentication authentication);

    List<SimplifiedProjectDto> getAllProjects(Pageable pageable);

    ProjectDto getProjectDetailsById(Long id);

    ProjectDto updateProjectById(Long id, UpdateProjectRequestDto requestDto);

    void deleteProjectById(Long id);
}
