package taskmanagementsystem.service.project;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskmanagementsystem.dto.projectdto.CreateProjectRequestDto;
import taskmanagementsystem.dto.projectdto.ProjectDto;
import taskmanagementsystem.dto.projectdto.SimplifiedProjectDto;
import taskmanagementsystem.dto.projectdto.UpdateProjectRequestDto;
import taskmanagementsystem.exception.ProjectNotFoundException;
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
    public ProjectDto createProject(
            CreateProjectRequestDto requestDto,
            Authentication authentication) {
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
    public List<SimplifiedProjectDto> getAllProjects(
            Pageable pageable,
            Authentication authentication) {
        User user = getUser(authentication);
        List<Project> userProjects = projectRepository.findAllByUsersId(user.getId());
        return userProjects.stream()
                .map(projectMapper::toSimplifiedDto)
                .toList();
    }

    @Override
    public ProjectDto getProjectDetailsById(
            Long id,
            Authentication authentication) {
        Project userProjectById = getUserProjectById(id, authentication)
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Can't get details about project by this id: " + id));
        return projectMapper.toDto(userProjectById);
    }

    @Override
    public ProjectDto updateProjectById(
            Long id,
            UpdateProjectRequestDto requestDto,
            Authentication authentication) {
        Project userProjectById = getUserProjectById(id, authentication)
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Can't update project by this id: " + id));
        projectMapper.updateProjectFromDto(requestDto, userProjectById);
        return projectMapper.toDto(projectRepository.save(userProjectById));
    }

    @Override
    public void deleteProjectById(
            Long id,
            Authentication authentication) {
        Project userProjectById = getUserProjectById(id, authentication)
                .orElseThrow(() -> new ProjectNotFoundException(
                        "Can't delete project by this id: " + id));
        projectRepository.deleteById(userProjectById.getId());
    }

    private Optional<Project> getUserProjectById(
            Long id,
            Authentication authentication) {
        User user = getUser(authentication);
        return projectRepository.findByIdAndUsersId(id, user.getId());
    }

    private User getUser(
            Authentication authentication) {
        return (User) customUserDetailService.loadUserByUsername(authentication.getName());
    }
}
