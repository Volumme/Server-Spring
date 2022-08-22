package Alpha.alphaspring.repository;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.SubRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubRoutineRepository extends JpaRepository<SubRoutine, Long> {
    SubRoutine save(SubRoutine subRoutine);
    Optional<SubRoutine> findById(String id);
    Optional<SubRoutine> findByName(String name);
    List<SubRoutine> findAll();
    Optional<SubRoutine> findByRoutineId(Long userId);
}
