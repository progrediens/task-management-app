package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.TrueFalseConverter;

@Entity
@Table(name = "projects")
@SoftDelete(columnName = "is_deleted",
        converter = TrueFalseConverter.class)
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private String description;
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;
  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;
  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;

  public enum Status {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED
  }
}
