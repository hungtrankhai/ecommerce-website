package com.trankhaihung.ecommerce.controller.rest;

import com.trankhaihung.ecommerce.entity.User;
import com.trankhaihung.ecommerce.exception.BadRequestException;
import com.trankhaihung.ecommerce.payload.*;
import com.trankhaihung.ecommerce.repository.UserRepository;
import com.trankhaihung.ecommerce.security.CurrentUser;
import com.trankhaihung.ecommerce.security.JwtTokenProvider;
import com.trankhaihung.ecommerce.security.UserPrincipal;
import com.trankhaihung.ecommerce.payload.UserIdentityAvailability;
import com.trankhaihung.ecommerce.payload.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/information")
    public ResponseEntity<?> getUserFromToken(@RequestParam(value = "jwtToken") String jwtToken) {
        boolean isValidToken = tokenProvider.validateToken(jwtToken);
        if (!isValidToken) {
            throw new BadRequestException("Invalid JWT Token");
        }

        Long userId = tokenProvider.getUserIdFromJWT(jwtToken);
        User user = userRepository.getUserById(userId);
        return ResponseEntity.ok(user);

    }
}
