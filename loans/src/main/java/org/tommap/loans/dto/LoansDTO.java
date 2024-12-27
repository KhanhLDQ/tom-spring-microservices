package org.tommap.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loans",
        description = "Schema to hold Loan information"
)
@Data
public class LoansDTO {
    @Schema(description = "Mobile number of the customer", example = "9876543210")
    @NotEmpty(message = "mobile number cannot be null or empty")
    @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(description = "Loan number of the customer", example = "123456789012")
    @NotEmpty(message = "loan number cannot be null or empty")
    @Pattern(regexp = "^\\d{12}$", message = "loan number should be 12 digits")
    private String loanNumber;

    @Schema(description = "Loan type of the customer", example = "Home Loan")
    @NotEmpty(message = "loan type cannot be null or empty")
    private String loanType;

    @Schema(description = "Total loan amount of the customer", example = "100000")
    @Positive(message = "total loan should be greater than zero")
    private Integer totalLoan;

    @Schema(description = "Total amount paid by the customer", example = "50000")
    @PositiveOrZero(message = "amount paid should be greater than or equal to zero")
    private Integer amountPaid;

    @Schema(description = "Total outstanding amount of the customer", example = "50000")
    @PositiveOrZero(message = "outstanding amount should be greater than or equal to zero")
    private Integer outstandingAmount;
}
