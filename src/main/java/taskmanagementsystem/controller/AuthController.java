package taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import taskmanagementsystem.dto.userdto.UserLoginRequestDto;
import taskmanagementsystem.dto.userdto.UserLoginResponseDto;
import taskmanagementsystem.dto.userdto.UserRegistrationRequestDto;
import taskmanagementsystem.dto.userdto.UserResponseDto;
import taskmanagementsystem.security.AuthenticationService;
import taskmanagementsystem.service.UserService;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register on a website",
            description = "Allow users to register into website")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto authenticate(@RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

}
