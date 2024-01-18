package taskmanagementsystem.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(
        @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
        @NotBlank
        @Size(min = 4, max = 50)
        String email,
        @NotBlank
        @Size(min = 8, max = 80)
        String password,
        @NotBlank
        @Size(min = 2, max = 50)
        String firstName,
        @NotBlank
        @Size(min = 2, max = 50)
        String lastName
) {
}
