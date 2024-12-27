package org.tommap.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST APIs Documentation",
				description = "Tommap Loans microservice REST APIs Documentation",
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
				description = "Tommap Loans microservice Wiki",
				url = "https://tommap.com"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
