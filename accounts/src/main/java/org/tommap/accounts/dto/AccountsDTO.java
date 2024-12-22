package org.tommap.accounts.dto;

import lombok.Data;

/*
    - @Data
        + combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor
        + do not use this annotation inside entity classes
            - override equals() and hashCode() sometimes can create issues with Spring Data JPA framework
 */
@Data
public class AccountsDTO {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
