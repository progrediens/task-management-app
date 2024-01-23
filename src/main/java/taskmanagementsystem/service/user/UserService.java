package taskmanagementsystem.service.user;

import org.springframework.security.core.Authentication;
import taskmanagementsystem.dto.userdto.UserInfoResponseDto;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.dto.userdto.UserUpdateInfoRequestDto;
import taskmanagementsystem.dto.userdto.UserUpdateRoleRequestDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserInfoResponseDto getInfo(Authentication authentication);

    UserResponseDto updateUserRole(Long id, UserUpdateRoleRequestDto newRole);

    UserInfoResponseDto updateProfileInfo(UserUpdateInfoRequestDto requestDto,
                                          Authentication authentication);
}
