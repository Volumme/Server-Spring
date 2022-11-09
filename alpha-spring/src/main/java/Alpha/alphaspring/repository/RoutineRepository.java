package Alpha.alphaspring.repository;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    Routine save(Routine routine);
    Optional<Routine> findByIdAndUser(Long id, User user);
    Optional<Routine> findByName(String name);
    Optional<Routine> findByNameAndUser(String name, User user);
    List<Routine> findByUser_UsernameAndNameAndDescription(String username, String routinename, String category);
    List<Routine> findAll();
    Optional<Routine> findByUser_Id(Long id);
    Optional<Routine> findByUser_Username(String username);
    List<Routine> findByIsRecommended(boolean isRecommended);
    List<Routine> findByDescription(String description);
    List<Routine> findByUser_UsernameAndUser_Provider(String username, String provider);
}
