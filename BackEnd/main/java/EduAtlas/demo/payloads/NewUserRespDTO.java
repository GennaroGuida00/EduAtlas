package EduAtlas.demo.payloads;

import EduAtlas.demo.enums.RuoloUtente;

public record NewUserRespDTO(
        long user_id,
        String name,
        String surname,
        String email,
        String password,
        RuoloUtente ruoloUtente
) {
}
