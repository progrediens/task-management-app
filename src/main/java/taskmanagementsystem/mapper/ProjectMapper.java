package taskmanagementsystem.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import taskmanagementsystem.dto.CreateProjectRequestDto;
import taskmanagementsystem.dto.ProjectDto;
import taskmanagementsystem.dto.SimplifiedProjectDto;
import taskmanagementsystem.dto.UpdateProjectRequestDto;
import taskmanagementsystem.model.Project;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface ProjectMapper {
    ProjectDto toDto(Project project);

    Project toEntity(CreateProjectRequestDto requestDto);

    SimplifiedProjectDto toSimplifiedDto(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromDto(UpdateProjectRequestDto requestDto, @MappingTarget Project project);
}
