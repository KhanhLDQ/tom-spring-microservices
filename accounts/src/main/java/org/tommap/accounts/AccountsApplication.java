package org.tommap.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") //passing the bean name of the AuditorAware implementation
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST APIs Documentation",
                description = "Tommap Accounts microservice REST APIs Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Tommap",
                        email = "tom@gmail.com",
                        url = "https://tommap.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://tommap.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Tommap Accounts microservice Wiki",
                url = "https://tommap.com"
        )
)
@ConfigurationPropertiesScan
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
