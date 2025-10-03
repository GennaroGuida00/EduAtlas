package EduAtlas.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record NewCreditDTO(
        @Positive
        int year_1_credits,

        @Positive
        int year_2_credits,
        @Positive
        int year_3_credits,

        boolean year_4_is_additional,
        @Positive
        Integer year_4_credits,
        boolean year_5_is_additional,
        @Positive
        Integer year_5_credits,

        @Positive
        @NotEmpty(message = "il degree deve essere obbligatorio")
        Long degree_id
) {
}
