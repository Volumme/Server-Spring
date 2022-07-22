package Alpha.alphaspring.service;

import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

}
