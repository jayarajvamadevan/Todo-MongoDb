package com.Mongo_Db_Pro.demo_mongo_db.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Todo API Service",
                summary = "API contains Summary Info",
                description = "Service Requests",
                termsOfService = "For Study Purpose",
                version = "API/V1",
                contact = @Contact(name="Jayaraj Vamadevan",
                        email = "jayarajvs2050@gmail.com",
                        url = "https://github.com/jayarajvamadevan"),
                license = @License(name = "Jayaraj Vamadevan 123")
        ),
        servers = @Server(description = "TestEnv",url = "http://localhost:5469"),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
