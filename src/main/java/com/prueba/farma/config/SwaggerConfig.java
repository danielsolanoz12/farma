package com.prueba.farma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    /**
     * @return
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Usuarios y Tickets")
                        .version("2.0")
                        .description("Documentación de la API para gestionar usuarios y tickets"))
                        .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                        .components(new io.swagger.v3.oas.models.Components()
                            .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")));
    }

}
