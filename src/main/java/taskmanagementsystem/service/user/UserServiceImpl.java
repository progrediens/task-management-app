package taskmanagementsystem.service.user;

import java.util.Collections;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskmanagementsystem.dto.userdto.UserInfoResponseDto;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.dto.userdto.UserUpdateInfoRequestDto;
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
    public UserInfoResponseDto getInfo(Long id) {
        return userMapper.toInfoResponse(userRepository.getReferenceById(id));
    }

    @Override
    public UserResponseDto updateUserRole(Long id, String role) {
        User user = getUserById(id);
        Role newRole = roleRepository.findRoleByName(Role.RoleName.valueOf(role)).orElseThrow(
                () -> new EntityNotFoundException(
                        "There is no role with name " + role));
        user.setRoles(Set.of(newRole));
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserInfoResponseDto updateProfileInfo(Long id, UserUpdateInfoRequestDto requestDto) {
        User user = getUserById(id);
        userMapper.toUserUpdate(requestDto, user);
        return userMapper.toInfoResponse(userRepository.save(user));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "There is no user with id: " + id));
    }
}
