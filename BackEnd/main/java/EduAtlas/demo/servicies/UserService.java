package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.exceptions.BadRequestException;
import EduAtlas.demo.exceptions.NotFoundException;
import EduAtlas.demo.payloads.NewUserDTO;
import EduAtlas.demo.payloads.NewUserRespDTO;
import EduAtlas.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder bcrypt;

    public User save(NewUserDTO userDTO){
        User user=new User();
        user.setName(userDTO.name());
        user.setSurname(userDTO.surname());
        user.setEmail(userDTO.email());
        user.setPassword(bcrypt.encode(userDTO.password()));
        user.setRuoloUtente(userDTO.ruoloUtente());
        return userRepository.save(user);
    }

    public List<NewUserRespDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new NewUserRespDTO(
                        user.getId_user(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRuoloUtente()
                ))
                .collect(Collectors.toList());
    }



    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new NotFoundException(email));
    }

    public User findById(long id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void deleteUser(long id){
        User found=userRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        userRepository.delete(found);
    }

    public User findByIdAndUpdate(long id, NewUserDTO userDTO){
        User found=userRepository.findById(id).orElseThrow(()->new NotFoundException(id));

        if(!found.getEmail().equals(userDTO.email())){
            userRepository.findByEmail(userDTO.email()).ifPresent(user -> {throw new BadRequestException("L'email "+ userDTO.email() +" è già in uso");});
        }
        found.setName(userDTO.name());
        found.setSurname(userDTO.surname());
        found.setEmail(userDTO.email());
        found.setPassword(bcrypt.encode(userDTO.password()));
        found.setRuoloUtente(userDTO.ruoloUtente());
        return userRepository.save(found);
    }

}
