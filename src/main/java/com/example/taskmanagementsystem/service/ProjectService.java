package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.CreateProjectRequestDto;
import com.example.taskmanagementsystem.dto.ProjectDto;
import com.example.taskmanagementsystem.dto.SimplifiedProjectDto;
import com.example.taskmanagementsystem.dto.UpdateProjectRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    ProjectDto createProject(CreateProjectRequestDto project);

    List<SimplifiedProjectDto> getAllProjects(Pageable pageable);

    ProjectDto getProjectDetailsById(Long id);

    ProjectDto updateProjectById(Long id, UpdateProjectRequestDto requestDto);

    void deleteProjectById(Long id);
}
