package EduAtlas.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record NewDegreeDTO(

        @NotEmpty(message = "il nome deve essere obbligatorio")
        String name,

        @Positive
        int duration_year,

        @Positive
        int eqf_level,

        @Positive
        @NotEmpty(message = "il paese deve essere obbligatorio")
        Long country_id
) {
}
