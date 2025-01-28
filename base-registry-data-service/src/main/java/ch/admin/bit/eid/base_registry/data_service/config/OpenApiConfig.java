/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.base_registry.data_service.config;

import ch.admin.bit.eid.BaseRegistryDataServiceApplication;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Base Registry Data Service",
                description = "Fill with a documentation",
                contact = @Contact(
                        email = "eid@bit.admin.ch",
                        name = "eID",
                        url = "https://confluence.bit.admin.ch/display/EIDTEAM"
                )
        ),
        externalDocs = @ExternalDocumentation(url = "https://confluence.bit.admin.ch/display/EIDTEAM/Datastore/data-service")
)
@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API")
                .pathsToMatch("/api/**")
                .packagesToScan(BaseRegistryDataServiceApplication.class.getPackageName())
                .build();
    }
}
