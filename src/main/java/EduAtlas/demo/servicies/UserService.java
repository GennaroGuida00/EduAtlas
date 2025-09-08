package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.enums.RuoloUtente;
import EduAtlas.demo.exceptions.NotFoundExceptions;
import EduAtlas.demo.payloads.NewUserDTO;
import EduAtlas.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(NewUserDTO userDTO){
        User user=new User();
        user.setName(userDTO.name());
        user.setSurname(userDTO.surname());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRuoloUtente(userDTO.ruoloUtente());
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new NotFoundExceptions(email));
    }

    public User findById(long id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundExceptions(id));
    }

    public void deleteUser(long id){
        User found=userRepository.findById(id).orElseThrow(()->new NotFoundExceptions(id));
        userRepository.delete(found);
    }
}
