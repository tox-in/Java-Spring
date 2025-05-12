package eucl.tokengenerator.tokengeneratorapplication.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name can't exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Size(max = 100, message = "Email can't exceed 100 characters")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 digits")
        @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
        String phone,

        @NotBlank(message = "National ID is required")
        @Size(min = 16, max = 16, message = "National ID must be 16 digits")
        @Pattern(regexp = "^\\d{16}$", message = "National ID must be exactly 16 digits")
        String nationalId,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
        String password,

        @NotBlank(message = "Confirm password is required and must be the same as Password")
        @Size(min = 6, max = 40, message = "Confirm Password must be the same as the Password")
        String confirmPassword
) {
}
