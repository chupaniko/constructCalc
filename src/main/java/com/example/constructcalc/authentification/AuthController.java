package com.example.constructcalc.authentification;

import com.example.constructcalc.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public String auth (@RequestBody AuthRequest request) {
        return authService.auth(request)
                ? request.getUsername() + " authorized successfully"
                : "unable to authorize " + request.getUsername();
    }
}
