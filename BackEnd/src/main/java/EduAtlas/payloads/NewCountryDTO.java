package EduAtlas.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewCountryDTO(
        @NotEmpty(message = "obbligatorio")
        String name,
        @NotNull(message = "l'anno è obbligatorio")
        @Positive(message = "l'importo degli anni deve essere positivo")
        int years_compulsary_schooling

) {
}
