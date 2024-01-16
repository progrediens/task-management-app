package com.example.taskmanagementsystem.mapper;

import com.example.taskmanagementsystem.dto.CreateProjectRequestDto;
import com.example.taskmanagementsystem.dto.ProjectDto;
import com.example.taskmanagementsystem.dto.SimplifiedProjectDto;
import com.example.taskmanagementsystem.dto.UpdateProjectRequestDto;
import com.example.taskmanagementsystem.model.Project;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
