package br.com.fiap.restaurantmanagement.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Turma: 2ADJT - Grupo 57 - Repositório do Projeto");
        contact.setUrl("https://github.com/brunolimadev/restaurant-management");

        Info info = new Info()
                .title("Sistema de Reservas e Avaliação de Restaurantes")
                .contact(contact)
                .version("1.0")
                .description("Tech Challenger (Fase 3)  (03/2024)");

        return new OpenAPI()
                .info(info);
    }
}
