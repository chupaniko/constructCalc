package com.example.constructcalc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String saveUser(String username, String password) {
        userRepository.save(new AppUser(username, (new BCryptPasswordEncoder()).encode(password)));
        return userRepository.existsByUsername(username) ? username : "huj";
    }

    public boolean authUser(AppUser requestAppUser) {
        AppUser user;
        user = userRepository.findUserByUsername(requestAppUser.getUsername())
                .orElse(new AppUser("", ""));
        return Objects.equals(user.getUsername(), requestAppUser.getUsername()) && (new BCryptPasswordEncoder()).matches(requestAppUser.getPassword(), user.getPassword());
    }
}
