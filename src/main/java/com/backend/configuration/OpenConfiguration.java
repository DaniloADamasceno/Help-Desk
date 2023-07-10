package com.backend.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {            // link para acessar o SWAGGER: http://localhost:8080/swagger-ui.html
        return new OpenAPI()
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Help DESK")
                                .version("1.0.0")
                                .description("Help Desk com utilização da  linguagem Java com Spring Boot e Angular 12")
                );
    }
}
