package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.User;
import EduAtlas.demo.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public User getByEmail(@RequestParam String email){
        return userService.findByEmail(email);

    }

    @DeleteMapping("/{userId}")
    public void getByIdAndDelete(@PathVariable long userId){
        User found=userService.findById(userId);
        userService.deleteUser(found.getId_user());
    }
}
