package Alpha.alphaspring.repository;


import Alpha.alphaspring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User member);
    Optional<User> findById(String id);
    Optional<User> findByName(String name);
    List<User> findAll();

    Optional<User> findByUserId(String userId);
}
