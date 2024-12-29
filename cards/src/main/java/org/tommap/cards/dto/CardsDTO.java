package org.tommap.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CardsDTO {
    @Schema(description = "Mobile number of the customer", example = "9876543210")
    @NotEmpty(message = "mobile number cannot be null or empty")
    @Pattern(regexp = "^\\d{10}$", message = "mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(description = "Card number of the customer", example = "123456789012")
    @NotEmpty(message = "card number cannot be null or empty")
    @Pattern(regexp = "^\\d{12}$", message = "card number should be 12 digits")
    private String cardNumber;

    @Schema(description = "Card type of the customer", example = "Credit Card")
    @NotEmpty(message = "card type cannot be null or empty")
    private String cardType;

    @Schema(description = "Total card limit available against a card", example = "100000")
    @Positive(message = "total card limit should be greater than zero")
    private Integer totalLimit;

    @Schema(description = "Total amount used by a Customer", example = "1000")
    @PositiveOrZero(message = "total amount used should be greater than or equal to zero")
    private Integer amountUsed;

    @Schema(description = "Total available amount against a card", example = "90000")
    @PositiveOrZero(message = "available amount should be greater than or equal to zero")
    private Integer availableAmount;
}
