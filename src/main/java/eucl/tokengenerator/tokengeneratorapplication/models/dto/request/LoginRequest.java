package eucl.tokengenerator.tokengeneratorapplication.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "{login.email.required}")
        @Email(message = "{login.email.invalid}")
        @Email(message = "Email must be valid")
        String email,


        @NotBlank(message = "Password is required")
        String password
) {}
