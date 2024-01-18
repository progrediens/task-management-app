package taskmanagementsystem.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskmanagementsystem.dto.projectdto.CreateProjectRequestDto;
import taskmanagementsystem.dto.projectdto.ProjectDto;
import taskmanagementsystem.dto.projectdto.SimplifiedProjectDto;
import taskmanagementsystem.dto.projectdto.UpdateProjectRequestDto;
import taskmanagementsystem.service.project.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects")
@Validated
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectDto createProject(
            @RequestBody @Valid CreateProjectRequestDto requestDto,
            Authentication authentication) {
        return projectService.createProject(requestDto, authentication);
    }

    @GetMapping
    public List<SimplifiedProjectDto> getAllProjects(Pageable pageable,
                                                     Authentication authentication) {
        return projectService.getAllProjects(pageable, authentication);
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(
            @PathVariable @Valid Long id,
            Authentication authentication) {
        return projectService.getProjectDetailsById(id, authentication);
    }

    @PatchMapping("/{id}")
    public ProjectDto updateProjectById(
            @PathVariable @Valid Long id,
            @RequestBody UpdateProjectRequestDto requestDto,
            Authentication authentication) {
        return projectService.updateProjectById(id, requestDto, authentication);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(
            @PathVariable @Valid Long id,
            Authentication authentication) {
        projectService.deleteProjectById(id, authentication);
    }
}
