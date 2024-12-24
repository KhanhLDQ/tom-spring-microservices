package org.tommap.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
    - @Data
        + combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor
        + do not use this annotation inside entity classes
            - override equals() and hashCode() sometimes can create issues with Spring Data JPA framework
 */
@Data
public class AccountsDTO {
    @NotNull(message = "account number cannot be null")
    private Long accountNumber;

    @NotEmpty(message = "account type cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "branch address cannot be null or empty")
    private String branchAddress;
}
