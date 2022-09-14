package Alpha.alphaspring.repository;

import Alpha.alphaspring.domain.SubRoutine;
import Alpha.alphaspring.domain.WorkOut;
import Alpha.alphaspring.domain.WorkSet;
import Alpha.alphaspring.domain.WorkSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkSetRepository extends JpaRepository<WorkSet, Long> {
    WorkSet save(WorkSet workSet);
    List<WorkSet> findAll();
    List<WorkSet> findBySubRoutine_Id(Long id);

}
