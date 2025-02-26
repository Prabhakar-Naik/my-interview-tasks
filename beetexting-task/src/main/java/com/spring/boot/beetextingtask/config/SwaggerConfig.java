package com.spring.boot.beetextingtask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title("BeeTexting API").version("1.0")
                .description("By Prabhakar.")
        ).servers(Arrays.asList(new Server().url("http://localhost:8080").description("local"),
                new Server().url("http://localhost:8082").description("live")));
    }
}
