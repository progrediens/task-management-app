package taskmanagementsystem.dto.userdto;

import java.util.List;
import taskmanagementsystem.model.Project;

public record UserInfoResponseDto(String email,
                                  String firstName,
                                  String lastName,
                                  List<Project> projects) {
}
