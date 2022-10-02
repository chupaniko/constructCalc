package com.example.constructcalc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/api/helloworld")
    public String helloWorld(){
        return "Привет, я калуькултор \"НеСтройСам\"";
    }
}
