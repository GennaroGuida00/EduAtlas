package EduAtlas.payloads;

import EduAtlas.enums.RuoloUtente;

public record NewUserRespDTO(
        long user_id,
        String name,
        String surname,
        String email,
        String password,
        RuoloUtente ruoloUtente
) {
}
