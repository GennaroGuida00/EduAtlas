package EduAtlas.payloads;

import jakarta.validation.constraints.Email;

public record NewLoginDTO(
        @Email
        String email,
        String password
) {
}
