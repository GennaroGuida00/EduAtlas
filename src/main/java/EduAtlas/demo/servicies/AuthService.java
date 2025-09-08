package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.exceptions.UnauthorizedException;
import EduAtlas.demo.payloads.NewLoginDTO;
import EduAtlas.demo.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JWTTools jwtTools;

    public String checkCredentialAndGenerateToken(NewLoginDTO loginDTO){
        User found=userService.findByEmail(loginDTO.email());

        if(found.getPassword().equals(loginDTO.password())){
            return jwtTools.createToken(found);
        }else
            throw new UnauthorizedException("la password dell'utente non è corretta");
    }
}
