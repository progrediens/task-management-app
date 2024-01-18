package taskmanagementsystem.service.user;

import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
