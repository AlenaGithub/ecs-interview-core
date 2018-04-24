package com.ecs.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecs.interview.service.AnagramCalculator;

@Configuration
public class AppConfig {
    @Bean
    public AnagramCalculator AnagramCalculator() {
        return new AnagramCalculator();
    }
}