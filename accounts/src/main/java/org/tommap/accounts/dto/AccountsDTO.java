package org.tommap.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
    - @Data
        + combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor
        + do not use this annotation inside entity classes
            - override equals() and hashCode() sometimes can create issues with Spring Data JPA framework
 */
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDTO {
    @Schema(description = "Account number of the customer")
    @NotNull(message = "account number cannot be null")
    private Long accountNumber;

    @Schema(description = "Account type of the customer", example = "Savings")
    @NotEmpty(message = "account type cannot be null or empty")
    private String accountType;

    @Schema(description = "Branch address of the customer", example = "Vietnam")
    @NotEmpty(message = "branch address cannot be null or empty")
    private String branchAddress;
}
