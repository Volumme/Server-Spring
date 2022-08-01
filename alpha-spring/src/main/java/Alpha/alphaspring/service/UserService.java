package Alpha.alphaspring.service;

import Alpha.alphaspring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // register user
    void join(User user);

    // find user by id
    User findUser(String userId) throws Exception;

    // return every user list
    List<User> findUsers() throws Exception;
}
