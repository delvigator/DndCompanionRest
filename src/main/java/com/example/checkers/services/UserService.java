//package com.example.checkers.services;
//
//import com.example.checkers.models.User;
//import com.example.checkers.models.enums.Role;
//import com.example.checkers.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.security.Principal;
//
//@Service
//@Slf4j
//@Transactional
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//   // private final PasswordEncoder passwordEncoder;
//
//    public boolean createUser(User user) {
//        log.info("called create user");
//        String username = user.getUsername();
//        if (userRepository.findByUsername(username)!= null) return false;
//        user.setActive(true);
//        user.setPassword(user.getPassword());
//        user.getRoles().add(Role.ROLE_USER);
//        log.info("Saving new User with username: {}", username);
//        userRepository.save(user);
//        return true;
//    }
//    public User getUserByPrincipal(Principal principal) {
//        if (principal == null) return new User();
//        return userRepository.findByUsername(principal.getName());
//    }
//}
