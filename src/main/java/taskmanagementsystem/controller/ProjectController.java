package taskmanagementsystem.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskmanagementsystem.dto.CreateProjectRequestDto;
import taskmanagementsystem.dto.ProjectDto;
import taskmanagementsystem.dto.SimplifiedProjectDto;
import taskmanagementsystem.dto.UpdateProjectRequestDto;
import taskmanagementsystem.service.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects")
@Validated
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectDto createProject(
            @RequestBody @Valid CreateProjectRequestDto requestDto) {
        return projectService.createProject(requestDto);
    }

    @GetMapping
    public List<SimplifiedProjectDto> getAllProjects(Pageable pageable) {
        return projectService.getAllProjects(pageable);
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable @Valid Long id) {
        return projectService.getProjectDetailsById(id);
    }

    @PutMapping("/{id}")
    public ProjectDto updateProjectById(
            @PathVariable @Valid Long id, @RequestBody UpdateProjectRequestDto requestDto) {
        return projectService.updateProjectById(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable @Valid Long id) {
        projectService.deleteProjectById(id);
    }
}
