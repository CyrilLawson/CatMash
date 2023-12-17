package com.catmash.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {

        Contact myContact = new Contact();
        myContact.setName("Cyril LAWSON");
        myContact.setEmail("cyril.lawson2022@gmail.com");

        Info information = new Info()
                .title("CatMash Management API")
                .version("1.0")
                .description("This API exposes endpoints to vote for cats")
                .contact(myContact);
        return new OpenAPI().info(information);
    }
}
