package hrs.parcel.management.api.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI hrsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HRS Parcel Management API")
                        .description("API for managing parcels and guests")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub")
                        .url("https://github.com/your-repo"));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("guest-api")
                .packagesToScan("hrs.parcel.management.api.controller")
                .build();
    }
}