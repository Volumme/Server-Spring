package Alpha.alphaspring.controller;

import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> list(User user){
        List<User> users = userService.findUsers();
        return users;
    }
}
