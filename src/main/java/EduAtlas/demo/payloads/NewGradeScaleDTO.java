package EduAtlas.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewGradeScaleDTO(
        @Positive
        double min_value,

        @Positive
        double max_value,

        @NotNull
        String grade_value,

        @NotEmpty(message = "il paese deve essere obbligatorio")
        Long country_id
) {
}
