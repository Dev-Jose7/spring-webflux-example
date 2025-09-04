package com.example.spring_webflux.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
        "com.example.spring_webflux.domain.usecase",
        "com.example.spring_webflux.gateways.repository",
        "com.example.spring_webflux.infrastructure.gateways.mapper",
        "com.example.spring_webflux.infrastructure.entrypoints.controllers"
},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class AppConfig {


}
