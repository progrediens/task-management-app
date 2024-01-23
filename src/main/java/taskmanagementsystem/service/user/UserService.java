package taskmanagementsystem.service.user;

import taskmanagementsystem.dto.userdto.UserInfoResponseDto;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.dto.userdto.UserUpdateInfoRequestDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserInfoResponseDto getInfo(Long id);

    UserResponseDto updateUserRole(Long id, String role);

    UserInfoResponseDto updateProfileInfo(Long id, UserUpdateInfoRequestDto requestDto);
}
