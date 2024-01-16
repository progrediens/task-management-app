package taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanagementsystem.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
