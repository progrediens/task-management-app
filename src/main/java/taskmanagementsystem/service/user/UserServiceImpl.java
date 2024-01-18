package taskmanagementsystem.service.user;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
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

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = userMapper.toUser(requestDto);
        user.setRoles(Collections.singleton(roleRepository
                .findRoleByName(Role.RoleName.ROLE_USER)));
        user.setEmail(requestDto.email());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
