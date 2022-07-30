package Alpha.alphaspring.controller;

import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
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

    @PostMapping("/users")
    public void check_token(@RequestBody String accessToken){
        System.out.println("AccessToken : ");
        System.out.println(accessToken);
    }
}
