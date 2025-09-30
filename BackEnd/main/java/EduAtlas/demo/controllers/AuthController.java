package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.payloads.NewLoginDTO;
import EduAtlas.demo.payloads.NewRespLoginDTO;
import EduAtlas.demo.payloads.NewUserDTO;
import EduAtlas.demo.servicies.AuthService;
import EduAtlas.demo.servicies.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
