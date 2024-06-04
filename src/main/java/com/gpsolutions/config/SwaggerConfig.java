package com.gpsolutions.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "GP Solutions test task",
                version = "1.0.0",
                description = "Service for managing hotels"))
public class SwaggerConfig {
}
