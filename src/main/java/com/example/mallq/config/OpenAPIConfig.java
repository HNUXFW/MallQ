package com.example.mallq.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI mallqOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MallQ API文档")
                        .description("轻量级消息驱动电商系统API文档")
                        .version("1.0")
                        .contact(new Contact()
                                .name("MallQ Team")
                                .email("support@mallq.com")));
    }
} 