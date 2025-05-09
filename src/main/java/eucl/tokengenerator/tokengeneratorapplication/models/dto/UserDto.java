package eucl.tokengenerator.tokengeneratorapplication.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
        Long id,

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name can't exceed 100 characters")
        String name

        @NotBlank(message = "Email is required")
Windsurf: Your IDE and extension versions are too old. Update your IDE to 2022.3+ and install the latest extension. For help, contact us at https://windsurf.com/support

) {
}
