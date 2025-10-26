package com.fitness.coaching.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fitnessCoachingOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");

        Info info = new Info()
                .title("PT Coaching Management API")
                .version("1.0.0")
                .description("REST API for Personal Training and Fitness Coaching Management System");

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}