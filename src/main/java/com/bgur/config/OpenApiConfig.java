package com.bgur.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: bgur
 * @package: com.bgur.config
 * @className: OpenApiConfig
 * @author: huihui
 * @description: OpenAPI 3.0 配置类，用于配置 Swagger 文档
 * @date: 2025/1/21 10:00
 * @version: 1.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BGUR 企业级商务管理系统 API")
                        .description("基于 Spring Boot 的企业级商务管理系统，提供完整的用户权限管理、商品管理、订单处理、积分系统等功能")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("huihui")
                                .email("your-email@example.com")
                                .url("https://github.com/your-username/bgur"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .components(new Components()
                        .addSecuritySchemes("Bearer", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT 认证，请在请求头中添加 Authorization: Bearer {token}")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"));
    }
}