package dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import org.springframework.lang.Nullable;

public record CreateProjectRequestDto(
        @NotBlank(message = "Name can't be null")
        String name,
        @Nullable
        String description,
        @NotBlank(message = "The date of the project's start needs to be included.")
        LocalDate startDate,
        @NotBlank(message = "For better time-management, include the date of project's end.")
        LocalDate endDate
) {
}
