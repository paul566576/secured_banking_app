package com.banking.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
		name = "Card",
		description = "Schema to hold Card information"
)
public class CardDto
{
	@NotEmpty(message = "CardNumber can't be null or empty")
	@Size(min = 16, max = 16, message = "The length of the cardNumber always must be 16")
	@Schema(description = "Card number of banking account", example = "5665566556655665")
	private String cardNumber;
	@NotEmpty(message = "MobileNumber can't be null or empty")
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
	@Schema(description = "Mobile number of the customer", example = "48571932698")
	private String mobileNumber;
	@NotEmpty(message = "CardType can't be null or empty")
	@Schema(description = "Card type of banking account", example = "CREDIT")
	private String cardType;
	@PositiveOrZero(message = "TotalLimit limit should be greater than zero")
	@Schema(description = "Total limit of the card", example = "9000")
	private int totalLimit;
	@PositiveOrZero(message = "AmountUsed limit should be greater than zero")
	@Schema(description = "Used amount of the card", example = "9000")
	private int amountUsed;
	@PositiveOrZero(message = "AvailableAmount limit should be greater than zero")
	@Schema(description = "Available amount to the card", example = "9000")
	private int availableAmount;
}