package Alpha.alphaspring.repository;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.StaticURI;
import Alpha.alphaspring.domain.SubSet;
import Alpha.alphaspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaticURIRepository extends JpaRepository<StaticURI, Long> {
    StaticURI save(StaticURI staticURI);

    Optional<StaticURI> findByName(String name);
}
