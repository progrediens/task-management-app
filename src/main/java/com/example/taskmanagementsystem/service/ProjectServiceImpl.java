package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.CreateProjectRequestDto;
import com.example.taskmanagementsystem.dto.ProjectDto;
import com.example.taskmanagementsystem.dto.SimplifiedProjectDto;
import com.example.taskmanagementsystem.dto.UpdateProjectRequestDto;
import com.example.taskmanagementsystem.mapper.ProjectMapper;
import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.repository.ProjectRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto createProject(CreateProjectRequestDto project) {
        Project entity = projectMapper.toEntity(project);
        entity.setStartDate(LocalDate.now());
        entity.setStatus(project.status() == null
                ? Project.Status.INITIATED
                : Project.Status.fromText(String.valueOf(project.status())));
        projectRepository.save(entity);
        return projectMapper.toDto(entity);
    }

    @Override
    public List<SimplifiedProjectDto> getAllProjects(Pageable pageable) {
        //implement logic,include finding all projects by user id
        return projectRepository.findAll().stream()
                .map(projectMapper::toSimplifiedDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectDetailsById(Long id) {
        return projectMapper.toDto(projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find project by this id: " + id)));
    }

    @Override
    public ProjectDto updateProjectById(Long id, UpdateProjectRequestDto requestDto) {
        Project projectFromDb = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find project by this id: " + id));
        projectMapper.updateProjectFromDto(requestDto, projectFromDb);
        return projectMapper.toDto(projectRepository.save(projectFromDb));
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
