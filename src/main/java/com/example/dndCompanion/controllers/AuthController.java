package com.example.dndCompanion.controllers;

import com.example.dndCompanion.dto.ChangeCharacterRequestDto;
import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.dto.RegistrationUserDto;
import com.example.dndCompanion.dto.jwt.JwtRequest;
import com.example.dndCompanion.services.AuthService;
import com.example.dndCompanion.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {
    @Autowired
     AuthService authService;
    @Autowired
      UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) throws JsonProcessingException {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody JwtRequest recoveryRequest) {
        return authService.changePassword(recoveryRequest);
    }

    @PostMapping("/recovery")
    public ResponseEntity<?> passwordRecovery(@RequestBody JwtRequest recoveryRequest) {
        return authService.passwordRecovery(recoveryRequest);
    }

    @PutMapping
    public ResponseEntity<?> putPassword(@RequestBody JwtRequest recoveryRequest) {
        return authService.passwordRecovery(recoveryRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) throws JsonProcessingException {
        return authService.createNewUser(registrationUserDto);
    }

    @GetMapping("/confirm/{token}")
    public String confirm(@PathVariable String token) {
        if (userService.verifyUser(token)) return "Почта успешно подтверждена";
        return "Неверный запрос";
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addCustomItem(@RequestBody ItemDto itemDto, Principal principal) {
        return userService.addCustomItem(principal, itemDto);
    }

    @PostMapping("/changeCharacter")
    public ResponseEntity<?> changeCharacter(@RequestBody ChangeCharacterRequestDto dto, Principal principal) {
        return userService.changeCharacter(principal, dto);
    }

}