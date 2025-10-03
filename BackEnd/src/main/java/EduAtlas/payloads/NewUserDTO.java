package EduAtlas.payloads;

import EduAtlas.enums.RuoloUtente;

public record NewUserDTO(
        String name,
        String surname,
        String email,
        String password,
        RuoloUtente ruoloUtente
) {
}
