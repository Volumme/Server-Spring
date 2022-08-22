package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;
import Alpha.alphaspring.service.UserServiceImpl;
import lombok.Setter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

@Setter
public class RoutineRegisterRequestDto implements IRequestDto<Routine, User>{
    private String name;
    @Override
    public Routine toEntity(User user) {
        return Routine.builder()
                .user(user)
                .name(name)
                .build();
    }
}
