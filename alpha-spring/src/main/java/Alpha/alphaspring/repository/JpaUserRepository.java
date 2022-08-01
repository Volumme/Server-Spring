package Alpha.alphaspring.repository;

import Alpha.alphaspring.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        User user = em.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> result = em.createQuery("select m from User m where m.name = :name",User.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> result = em.createQuery("select m from User m where m.userId = :userId",User.class)
                .setParameter("userId", userId)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }
}
