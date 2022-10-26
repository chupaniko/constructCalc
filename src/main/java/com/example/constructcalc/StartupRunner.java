package com.example.constructcalc;

import com.example.constructcalc.user.AppUser;
import com.example.constructcalc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<AppUser> userList = new ArrayList<>() {
            {
                add(new AppUser("ivanov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("ivanov128")));
                add(new AppUser("petrov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("petrov938")));
                add(new AppUser("sidorov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("sidorov238")));
                add(new AppUser("grishin@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("grishin289")));
                add(new AppUser("borisov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("borisov738")));
                add(new AppUser("nikolaev@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("nikolaev929")));
                add(new AppUser("abramov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("abramov264")));
                add(new AppUser("kuznetsov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("kuznetsov181")));
                add(new AppUser("kolobkov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("kolobkov391")));
                add(new AppUser("popov@ssau.ru",
                        bCryptPasswordEncoder
                                .encode("popov493")));

            }
        };
        userRepository.saveAll(userList);
    }
}
