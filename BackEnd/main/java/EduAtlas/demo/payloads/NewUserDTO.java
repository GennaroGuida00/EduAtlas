package EduAtlas.demo.payloads;

import EduAtlas.demo.enums.RuoloUtente;

public record NewUserDTO(
        String name,
        String surname,
        String email,
        String password,
        RuoloUtente ruoloUtente
) {
}
