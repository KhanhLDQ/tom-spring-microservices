package org.tommap.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "name cannot be null or empty")
    @Size(min = 5, max = 30, message = "name should be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "email should be in valid format")
    private String email;

    @NotEmpty(message = "mobile number cannot be null or empty")
    @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits")
    private String mobileNumber;

    @Valid
    private AccountsDTO accountsDTO;
}
