package service;

import dto.CreateProjectRequestDto;
import dto.ProjectDto;

public interface ProjectService {
  ProjectDto createProject(CreateProjectRequestDto project);
}
