package Alpha.alphaspring.controller;

import Alpha.alphaspring.Utils.CommonTokenUtils;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.service.UserServiceImpl;
import com.auth0.jwk.JwkException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "SERVER RUNNING!!";
    }

    @GetMapping("/session")
    public String session(ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/users")
    public List<User> userList(User user){
        try {
            List<User> users = userService.findUsers();
            return users;
        }
        catch (Exception e){
            return null;
        }
    }

    @PostMapping("/token")
    public boolean checkToken(@RequestBody String data){
        System.out.println("Token : ");
        System.out.println(data);
        try {
            return userService.checkKakaoToken(data);

        } catch (ParseException | JwkException e) {
            System.out.println("error! = " + e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public User registerUser(
//            @RequestParam(value = "id") String userId,
//            @RequestParam(required = false, value = "pw") String userPw,
//            @RequestParam(required = false, value = "name") String userName,
//            @RequestParam(required = false, value = "gender") String userGender,
//            @RequestParam(required = false, value = "phone_number") String userPhoneNumber,
//            @RequestParam(required = false, value = "age") int userAge
            @RequestHeader Map<String, Object> requestHeader,
            @RequestBody User user
            ) throws ParseException {
//        userService.join(new User(userId, userPw, userName, userGender, userPhoneNumber, userAge));
        String authorizationHeader = (String) requestHeader.get("authorization");
        String[] bearerHeader = authorizationHeader.split(" ");
        String jwtToken = bearerHeader[1];
        CommonTokenUtils tokenUtils = new CommonTokenUtils();

        String subject = tokenUtils.getSubject(jwtToken);
        String provider = tokenUtils.getIssuer(jwtToken);

        user.setUserId(subject);
        user.setProvider(provider);

        userService.join(user);
        return user;
    }

    @GetMapping("/user")
    public User findUserById(@RequestParam(value = "userId") String userId){
        try {
            return userService.findUser(userId);
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
    }
}
