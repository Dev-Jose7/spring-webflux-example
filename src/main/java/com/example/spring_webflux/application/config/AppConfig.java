package com.example.spring_webflux.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
        "com.example.spring_webflux.domain.usecase",
        "com.example.spring_webflux.gateways",
        "com.example.spring_webflux.infrastructure.persistence.gateways.mapper",
        "com.example.spring_webflux.infrastructure.entrypoints.controller"
},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class AppConfig {


}
