package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.jwt.JwtRequest;
import com.example.dndCompanion.dto.jwt.JwtResponseDto;
import com.example.dndCompanion.dto.RegistrationUserDto;
import com.example.dndCompanion.dto.UserDto;
import com.example.dndCompanion.dto.jwt.RecoveryResponseDto;
import com.example.dndCompanion.entity.User;
import com.example.dndCompanion.exeptions.AppError;
import com.example.dndCompanion.utils.JwtTokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;
    private final CharacterService characterService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public ResponseEntity<?> changePassword(@RequestBody JwtRequest recoveryRequest){
        if(userService.findByUsername(recoveryRequest.getUsername()).isEmpty()) return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),"Пользователь не найден"),HttpStatus.NOT_FOUND);
        userService.findByUsername(recoveryRequest.getUsername()).get().setPassword(bCryptPasswordEncoder.encode(recoveryRequest.getPassword()));
        userService.change(userService.findByUsername(recoveryRequest.getUsername()).get());
        return  ResponseEntity.ok( HttpStatus.OK);
    }
    public ResponseEntity<?> passwordRecovery(@RequestBody JwtRequest recoveryRequest){
        if(userService.findByUsername(recoveryRequest.getUsername()).isEmpty()) return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),"Пользователь не найден"),HttpStatus.NOT_FOUND);
        String code=generateRandomCode(6);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recoveryRequest.getUsername());
        mailMessage.setSubject("Восстановление пароля");
        mailMessage.setText("Ваш код восстановления: " + code);
        mailSender.send(mailMessage);
        return  ResponseEntity.ok(new RecoveryResponseDto(code));
    }
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) throws JsonProcessingException {
        if (userService.findByUsername(authRequest.getUsername()).isPresent() &&
                !userService.findByUsername(authRequest.getUsername()).get().isActive()) {
            if( userService.findByUsername(authRequest.getUsername()).get().getExpiryDate().before(new Date())) {
                log.info("Был обновлен токен");
                String token=UUID.randomUUID().toString();
                userService.findByUsername(authRequest.getUsername()).get().setVerificationToken(token);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(authRequest.getUsername());
                mailMessage.setSubject("Подтверждение регистрации");
                mailMessage.setText("Для подтверждения регистрации перейдите по ссылке: "
                        + "http://77.232.138.200/user/confirm/" + token);
                mailSender.send(mailMessage);
            }
            userService.findByUsername(authRequest.getUsername()).get().calculateExpiryDate(30);


            userService.change(userService.findByUsername(authRequest.getUsername()).get());
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Регистрация не была завершена. Подтвердите адрес почты."), HttpStatus.UNAUTHORIZED);
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        User user=userService.findByUsername(userDetails.getUsername()).get();
        return ResponseEntity.ok(new JwtResponseDto(token,characterService.getAllByUser(userDetails.getUsername()),user.getCurrentCharacter(),user.getCustomItems()));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) throws JsonProcessingException {
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedRequest);

//        String decodedRequest = new String(decodedBytes);
//        ObjectMapper objectMapper = new ObjectMapper();
//        RegistrationUserDto registrationUserDto = objectMapper.readValue(decodedRequest, RegistrationUserDto.class);
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        registrationUserDto.setPassword(bCryptPasswordEncoder.encode(registrationUserDto.getPassword()));
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()
                && !userService.findByUsername(registrationUserDto.getUsername()).get().isActive()) {
            userService.findByUsername(registrationUserDto.getUsername()).get().setVerificationToken(UUID.randomUUID().toString());
            userService.findByUsername(registrationUserDto.getUsername()).get().calculateExpiryDate(30);
            userService.change(userService.findByUsername(registrationUserDto.getUsername()).get());
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Регистрация этого пользователя не была завершена. Подтвердите почтовый адрес."), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);
//        String token = UUID.randomUUID().toString();
//        user.setConfirmationToken(token);
        //return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername()));
        return ResponseEntity.ok("Для подтверждения почты перейдите по ссылке в письме");
    }
    public static String generateRandomCode(int length) {
        String characters = "0123456789"; // Допустимые символы
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}
