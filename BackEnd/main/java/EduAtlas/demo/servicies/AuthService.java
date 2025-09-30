package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.enums.RuoloUtente;
import EduAtlas.demo.exceptions.UnauthorizedException;
import EduAtlas.demo.payloads.NewLoginDTO;
import EduAtlas.demo.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JWTTools jwtTools;

    @Autowired
    PasswordEncoder bcrypt;

    public String checkCredentialAndGenerateToken(NewLoginDTO loginDTO){
        User found=userService.findByEmail(loginDTO.email());
        if (found==null){
            throw new UnauthorizedException("utente non trovato");
        }
        if (!bcrypt.matches(loginDTO.password(), found.getPassword())){

        }

        if(!found.getRuoloUtente().equals(RuoloUtente.ADMIN)){
            throw new UnauthorizedException("l'utente non è autorizzato");

        }
            return jwtTools.createToken(found);

    }
}
