package EduAtlas.demo.payloads;

public record NewUserDTO(
        String name,
        String surname,
        String email,
        String password,
        String ruoloUtente
) {
}
