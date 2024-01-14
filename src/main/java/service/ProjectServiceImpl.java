package service;

import dto.CreateProjectRequestDto;
import dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import mapper.ProjectMapper;
import model.Project;
import org.springframework.stereotype.Service;
import repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto createProject(CreateProjectRequestDto project) {
        Project entity = projectMapper.toEntity(project);
        projectRepository.save(entity);
        return projectMapper.toDto(entity);
    }
}
