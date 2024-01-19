package taskmanagementsystem.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanagementsystem.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUsersId(Long userId);

    Optional<Project> findByIdAndUsersId(Long id, Long userId);
}
