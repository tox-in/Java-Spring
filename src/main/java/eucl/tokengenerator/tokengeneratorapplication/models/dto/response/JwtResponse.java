package eucl.tokengenerator.tokengeneratorapplication.models.dto.response;

import java.util.List;

public record JwtResponse(
        String token,
        String type,
        Long id,
        String name,
        String email,
        String phone,
        List<String> roles
) {
    public JwtResponse(String token, Long id, String name, String email, String phone, List<String> roles) {
        this(token, "Bearer", id, name, email, phone, roles);
    }
}
