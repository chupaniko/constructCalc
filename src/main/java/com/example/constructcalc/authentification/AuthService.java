package com.example.constructcalc.authentification;

import com.example.constructcalc.user.AppUser;
import com.example.constructcalc.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;

    public boolean auth(AuthRequest request) {
        return userService.authUser(new AppUser(request.getUsername(), request.getPassword()));
    }

    public AppUser getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}

