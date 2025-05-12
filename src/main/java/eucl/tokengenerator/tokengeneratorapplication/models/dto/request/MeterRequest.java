package eucl.tokengenerator.tokengeneratorapplication.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MeterRequest(
        @NotBlank(message = "Meter number is required")
        @Size(min = 6, max = 6, message = "Meter number must be exactly 6 characters")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Meter number must be alphanumeric")
        String meterNumber,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String userEmail
) {
}
