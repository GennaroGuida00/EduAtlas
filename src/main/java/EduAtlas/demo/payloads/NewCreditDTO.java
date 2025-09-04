package EduAtlas.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record NewCreditDTO(
        @Positive
        int credit_value,

        @Positive
        int year,

        @Positive
        @NotEmpty(message = "il degree deve essere obbligatorio")
        Long degree_id
) {
}
