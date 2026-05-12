package br.sgc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Informe o JWT obtido em POST /api/auth/login")))
                .info(new Info()
                        .title("SGC - Sistema de Gestão Comercial de Papelaria")
                        .version("1.0")
                        .description("API REST para gerenciamento de clientes, produtos e vendas")
                        .contact(new Contact()
                                .name("Érik, Miguel, Luca")
                                .email("contato@sgc.com")));
    }
}