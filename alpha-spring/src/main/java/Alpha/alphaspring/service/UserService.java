package Alpha.alphaspring.service;

import Alpha.alphaspring.domain.User;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// 비지니스 로직 구현

public interface UserService {
    // register user
    void join(Map<String, Object> header, User user) throws ParseException;

    // find user by id
    User findUser(String userId) throws Exception;

    // return every user list
    List<User> findUsers() throws Exception;
}
