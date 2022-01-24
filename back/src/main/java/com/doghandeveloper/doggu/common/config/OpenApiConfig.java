package com.doghandeveloper.doggu.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {
    @Value("${springdoc.config.title}")
    private String TITLE;
    @Value("${springdoc.config.description}")
    private String DESCRIPTION;
    @Value("${springdoc.config.license}")
    private String LICENSE;
    @Value("${springdoc.config.license-url}")
    private String LICENSE_URL;
    @Value("${springdoc.config.authorization}")
    private String AUTHORIZATION;
    @Value("${springdoc.config.authorization-type}")
    private String AUTHORIZATION_TYPE;


    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion, @Value("${server.servlet.context-path}") String contextPath){
        Info info = new Info().title(TITLE)
                .version(appVersion)
                .description(DESCRIPTION)
                .license(new License().name(LICENSE).url(LICENSE_URL));
        Components components = new Components()
                .addSecuritySchemes(AUTHORIZATION,
                        new SecurityScheme()
                        .name(AUTHORIZATION)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(AUTHORIZATION_TYPE)
                );
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath))
                .components(components)
                .info(info);
    }
}
