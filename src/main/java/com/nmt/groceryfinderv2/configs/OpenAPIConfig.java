package com.nmt.groceryfinderv2.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Ecommerce System Server API",
                description = "This API provides endpoints for managing users and their data.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Thuan Nguyen",
                        email = "thuanminh.2001286@gmail.com"
                ),
                license = @License(
                        name = "license name",
                        url = "http://www.apache.org/licenses/"
                ),
                termsOfService = "termsOfService"
        ),
        servers = {
                @Server(url = "http://localhost:3434", description = "Development server"),
                @Server(url = "http://localhost:3434", description = "Production server")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {

}