package taskmanagementsystem.service.user;

import java.util.Collections;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskmanagementsystem.dto.userdto.UserInfoResponseDto;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.dto.userdto.UserUpdateInfoRequestDto;
import taskmanagementsystem.dto.userdto.UserUpdateRoleRequestDto;
import taskmanagementsystem.exception.EntityNotFoundException;
import taskmanagementsystem.exception.RegistrationException;
import taskmanagementsystem.mapper.UserMapper;
import taskmanagementsystem.model.Role;
import taskmanagementsystem.model.User;
import taskmanagementsystem.repository.RoleRepository;
import taskmanagementsystem.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = userMapper.toUser(requestDto);
        user.setRoles(Collections.singleton(roleRepository
                .findRoleByName(Role.RoleName.ROLE_USER).orElseThrow()));
        user.setEmail(requestDto.email());
        user.setUsername(requestDto.email().replaceAll("@.*","").trim());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserInfoResponseDto getInfo(Authentication authentication) {
        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        return userMapper.toInfoResponse(userRepository.getReferenceById(user.getId()));
    }

    @Override
    public UserResponseDto updateUserRole(Long id, UserUpdateRoleRequestDto newRole) {
        User user = getUserById(id);
        Role role = roleRepository.findRoleByName(Role.RoleName.valueOf(newRole.role()))
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "There is no role with name " + newRole.role()));
        user.setRoles(Set.of(role));
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserInfoResponseDto updateProfileInfo(UserUpdateInfoRequestDto requestDto,
                                                 Authentication authentication) {
        User user = getUser(authentication);
        userMapper.toUserUpdate(requestDto, user);
        return userMapper.toInfoResponse(userRepository.save(user));
    }

    private User getUser(Authentication authentication) {
        return (User) userDetailsService.loadUserByUsername(authentication.getName());

    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "There is no user with id: " + id));
    }
}
