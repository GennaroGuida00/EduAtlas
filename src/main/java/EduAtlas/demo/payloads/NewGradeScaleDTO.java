package EduAtlas.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record NewGradeScaleDTO(
        @Positive
        int min_value,

        @Positive
        int max_value,

        @NotEmpty(message = "il paese deve essere obbligatorio")
        Long country_id
) {
}
