//package Alpha.alphaspring.repository;
//
//import Alpha.alphaspring.domain.Routine;
//import Alpha.alphaspring.domain.User;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class RoutineRepositoryTest {
//
//    @Autowired
//    private RoutineRepository routineRepository;
//
//    @Test
//    void save() {
////        User user = User.builder().userId("jykim").age(24L).gender("male").name("jykim").phoneNumber("00").pw("asdasd").build();
////        Routine routine = Routine.builder().name("3times a week").user(user).build();
////        routineRepository.save(routine);
//////        org.assertj.core.api.Assertions.assertThat(testRoutine).isNotNull();
//
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void findByName() {
//    }
//
//    @Test
//    void findAll() {
//        List<Routine> items = routineRepository.findAll();
//        org.assertj.core.api.Assertions.assertThat(items).isEmpty();
//    }
//
//    @Test
//    void findByUser_Id() {
//    }
//}