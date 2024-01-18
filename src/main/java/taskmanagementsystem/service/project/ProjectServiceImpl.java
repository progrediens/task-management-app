package taskmanagementsystem.service.project;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskmanagementsystem.dto.projectdto.CreateProjectRequestDto;
import taskmanagementsystem.dto.projectdto.ProjectDto;
import taskmanagementsystem.dto.projectdto.SimplifiedProjectDto;
import taskmanagementsystem.dto.projectdto.UpdateProjectRequestDto;
import taskmanagementsystem.mapper.ProjectMapper;
import taskmanagementsystem.model.Project;
import taskmanagementsystem.model.User;
import taskmanagementsystem.repository.ProjectRepository;
import taskmanagementsystem.security.CustomUserDetailService;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final CustomUserDetailService customUserDetailService;

    @Transactional
    @Override
    public ProjectDto createProject(CreateProjectRequestDto requestDto, Authentication authentication) {
        User user = getUser(authentication);
        Project projectEntity = projectMapper.toEntity(requestDto);
        projectEntity.setStartDate(LocalDate.now());
        projectEntity.setStatus(requestDto.status() == null
                ? Project.Status.INITIATED
                : Project.Status.fromText(String.valueOf(requestDto.status())));
        projectEntity.addUser(user);
        projectRepository.save(projectEntity);
        return projectMapper.toDto(projectEntity);
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

    private User getUser(Authentication authentication) {
        return (User) customUserDetailService.loadUserByUsername(authentication.getName());
    }
}
