package eucl.tokengenerator.tokengeneratorapplication.models.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PurchaseRequest(
        @Size(min = 6, max = 6, message = "Meter number must be exactly 6 characters")
        @Pattern(regexp = "^[a-zA-Z0-9]{6}$", message = "Meter number must be alphanumeric")
        String meterNumber,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "100.00", message = "Minimum purchase amount is 100 RWF")
        @Digits(integer = 6, fraction = 2, message = "Amount must have up to 6 whole digits and 2 decimals")
        BigDecimal amount
) {
}
