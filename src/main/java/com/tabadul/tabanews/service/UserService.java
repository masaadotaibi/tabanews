package com.tabadul.tabanews.service;

import com.tabadul.tabanews.entities.User;
import com.tabadul.tabanews.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Remember that @Service is similar to @Component, but used as an indication to a service
// NOTE that @Component indicates that an annotated class is a component, that is, it wil be auto-detected
// for DI on Spring IoC container
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        boolean emailExist = userRepository
                                .findByEmail(user.getEmail()).
                                isPresent();

        if(emailExist) {
            throw new IllegalStateException("Email already registered!");
        }

        String encodedPassword = bCryptPasswordEncoder
                                    .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        // TODO: send confirmation token to email before enabling user

        return "User created";
    }
}
