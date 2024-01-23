package taskmanagementsystem.service.user;

import org.springframework.security.core.Authentication;
import taskmanagementsystem.dto.userdto.UserInfoResponseDto;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.dto.userdto.UserUpdateInfoRequestDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserInfoResponseDto getInfo(Authentication authentication);

    UserResponseDto updateUserRole(Long id, String role);

    UserInfoResponseDto updateProfileInfo(UserUpdateInfoRequestDto requestDto);
}
