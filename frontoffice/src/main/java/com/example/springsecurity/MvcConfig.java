package com.example.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("register");
    registry.addViewController("/hello").setViewName("hello");
    registry.addViewController("/register").setViewName("register");
    registry.addViewController("/login").setViewName("login");
  }

}