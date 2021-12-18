package com.example.foodapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    class Ping{
        private String test;
        public Ping(String msg){this.test = msg;}
        public String getTest(){return this.test;}
    }
    @GetMapping("/ping")
    public Ping ping(){return new Ping("Food API version 1.0 alive!");}
}
