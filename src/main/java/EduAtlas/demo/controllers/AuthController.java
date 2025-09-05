package EduAtlas.demo.controllers;

import EduAtlas.demo.payloads.NewLoginDTO;
import EduAtlas.demo.payloads.NewRespLoginDTO;
import EduAtlas.demo.servicies.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public NewRespLoginDTO login(NewLoginDTO loginDTO){
    String accessToken= authService.checkCredentialAndGenerateToken(loginDTO);
    return new NewRespLoginDTO(accessToken);
    }
}
