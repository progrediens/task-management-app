package controller;

import dto.CreateProjectRequestDto;
import dto.ProjectDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping(value = "/projects")
    public ProjectDto createProject(
            @RequestBody @Valid CreateProjectRequestDto requestDto) {
        return projectService.createProject(requestDto);
    }
}
