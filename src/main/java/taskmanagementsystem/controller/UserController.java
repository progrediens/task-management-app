package taskmanagementsystem.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskmanagementsystem.dto.userdto.UserInfoResponseDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.dto.userdto.UserUpdateInfoRequestDto;
import taskmanagementsystem.dto.userdto.UserUpdateRoleRequestDto;
import taskmanagementsystem.model.User;
import taskmanagementsystem.service.user.UserService;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public UserInfoResponseDto getInfo(Authentication authentication) {
        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        return userService.getInfo(user.getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public UserResponseDto updateUserRole(@PathVariable @Positive Long id,
                                          @RequestBody UserUpdateRoleRequestDto newRole) {
        return userService.updateUserRole(id, newRole.role());
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/update")
    public UserInfoResponseDto updateUserInfo(Authentication authentication,
                                                 @RequestBody UserUpdateInfoRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return userService.updateProfileInfo(user.getId(), requestDto);
    }
}
