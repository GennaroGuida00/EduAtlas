package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.enums.RuoloUtente;
import EduAtlas.demo.payloads.NewCreditDTO;
import EduAtlas.demo.payloads.NewUserDTO;
import EduAtlas.demo.payloads.NewUserRespDTO;
import EduAtlas.demo.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<NewUserRespDTO> filterUser() {
        return userService.findAll();
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public long register(@RequestBody @Validated NewUserDTO payload) {

        User newUser = userService.save(payload);
        return newUser.getId_user();
    }

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        return userService.findByEmail(email);

    }
    @GetMapping("/{userId}")
    public User getById(@PathVariable long id){
        return userService.findById(id);

    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable long userId){
        User found=userService.findById(userId);
        userService.deleteUser(found.getId_user());
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getByIdAndUpdate(@PathVariable long userId, @RequestBody NewUserDTO userDTO){
        return userService.findByIdAndUpdate(userId,userDTO);
    }
}
