package EduAtlas.controllers;

import EduAtlas.payloads.NewLoginDTO;
import EduAtlas.payloads.NewRespLoginDTO;
import EduAtlas.servicies.AuthService;
import EduAtlas.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public NewRespLoginDTO login(@RequestBody NewLoginDTO loginDTO) {
        String accessToken = authService.checkCredentialAndGenerateToken(loginDTO);
        return new NewRespLoginDTO(accessToken);
    }


    }
