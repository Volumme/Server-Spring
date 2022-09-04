package Alpha.alphaspring.repository;

import Alpha.alphaspring.domain.Routine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoutineRepositoryTest {

    @Autowired
    private RoutineRepository repo;

    @Transactional
    @Test
    void findByUser_Username() {
        Assertions.assertThat(repo.findByUser_UsernameAndUser_Provider("2362092730", "kakao"));
    }
}