package mapper;

import dto.CreateProjectRequestDto;
import dto.ProjectDto;
import model.Project;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface ProjectMapper {
  ProjectDto toDto(Project project);

  Project toEntity(CreateProjectRequestDto requestDto);
}
