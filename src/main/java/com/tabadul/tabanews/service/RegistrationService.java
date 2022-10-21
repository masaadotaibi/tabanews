package com.tabadul.tabanews.service;

import com.tabadul.tabanews.entities.User;
import com.tabadul.tabanews.util.UserRole;
import com.tabadul.tabanews.util.registration_util.EmailValidator;
import com.tabadul.tabanews.util.registration_util.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {

        boolean isValidMail = emailValidator.test(request.getEmail());

        if(!isValidMail) {
            throw new IllegalStateException("Email not valid");
        }

        userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getMobileNo(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER_ROLE
                )
        );

        return "It works";
    }
}
