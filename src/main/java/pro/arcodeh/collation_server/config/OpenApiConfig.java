package pro.arcodeh.collation_server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Umar Odeh Adejoh",
                        email = "admin@arcodeh.pro",
                        url = "https://arcodeh.pro"
                ),
                title = "Collation Server API Documentation",
                version = "1.0.0",
                description = "Election Results Collation Demo Server API"
        )
)
public class OpenApiConfig {
}
