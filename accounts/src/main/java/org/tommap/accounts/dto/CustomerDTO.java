package org.tommap.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Schema(
        name = "Customer",
        description = "Schema to hold Customer & Account information"
)
@Data
public class CustomerDTO {
    @Schema(description = "Name of the customer", example = "Tom")
    @NotEmpty(message = "name cannot be null or empty")
    @Size(min = 5, max = 30, message = "name should be between 5 and 30 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "tom@gmail.com")
    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "email should be in valid format")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "9876543210")
    @NotEmpty(message = "mobile number cannot be null or empty")
    @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    @Valid
    private AccountsDTO accountsDTO;
}
