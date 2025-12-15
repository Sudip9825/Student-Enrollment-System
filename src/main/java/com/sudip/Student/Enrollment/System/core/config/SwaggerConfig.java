package com.sudip.Student.Enrollment.System.core.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Student Enrollment System API"))
                .addSecurityItem(new SecurityRequirement()
                        .addList("StudentEnrollmentSystem"))
                .components(new Components()
                .addSecuritySchemes("StudentEnrollmentSystem", new SecurityScheme()
                        .name("StudentEnrollmentSystem")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }

}
