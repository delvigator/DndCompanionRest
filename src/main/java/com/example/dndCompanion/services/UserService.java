package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.ChangeCharacterRequestDto;
import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.dto.RegistrationUserDto;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.entity.Role;
import com.example.dndCompanion.entity.User;
import com.example.dndCompanion.exeptions.AppError;
import com.example.dndCompanion.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private final JavaMailSender mailSender;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

public ResponseEntity<?> changeCharacter(Principal principal, ChangeCharacterRequestDto dto){
        if(getUserByPrincipal(principal)==null) return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),"Пользователь не найден"),HttpStatus.NOT_FOUND);
        User user=getUserByPrincipal(principal);
        user.setCurrentCharacter(dto.getCharacterIndex());
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
}
    public ResponseEntity<?> addCustomItem(Principal principal, ItemDto dto){
        if(getUserByPrincipal(principal)==null) return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),"Пользователь не найден"),HttpStatus.NOT_FOUND);
        User user=getUserByPrincipal(principal);
        user.getCustomItems().add(Item.builder()
                        .name(dto.getName())
                        .rarity(dto.getRarity())
                        .type(dto.getType())
                        .weight(dto.getWeight())
                        .description(dto.getDescription())
                        .equipable(dto.isEquipable())
                .build());
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
public User change(User user){
        return userRepository.save(user);
}
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList())
        );
    }


    public User createNewUser(RegistrationUserDto dto) {
        User user = new User();
        Set<Role> roles=new HashSet<>();
        roles.add(Role.USER);
        String token = UUID.randomUUID().toString();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dto.getUsername());
        mailMessage.setFrom("dnd.companion13@gmail.com");
        mailMessage.setSubject("Подтверждение регистрации");
        mailMessage.setText("Для подтверждения регистрации перейдите по ссылке: "
                + "http://localhost:8080/user/confirm/"+ token);
        mailSender.send(mailMessage);
        if(Objects.equals(dto.getUsername(), "admin")) roles.add(Role.ADMIN);
        return userRepository.save(User.builder()
                        .username(dto.getUsername())
                        .verificationToken(token)
                        .active(false)
                        .expiryDate(user.calculateExpiryDate(30))
                        .currentCharacter(-1)
                        .password(dto.getPassword())
                        .roles(roles)
                .build());
    }
    public boolean verifyUser(String token){
       List<User> users= userRepository.findAll();
       for(User i: users){
           log.info(i.getUsername());
           log.info(i.getVerificationToken());
           log.info(token);
           if(!i.getVerificationToken().isBlank() && i.getVerificationToken().equals(token) && i.getExpiryDate().after(new Date())) {
               i.setActive(true);
               change(i);
               return true;
           }
       }

       return false;
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUsername(principal.getName());
    }
}
