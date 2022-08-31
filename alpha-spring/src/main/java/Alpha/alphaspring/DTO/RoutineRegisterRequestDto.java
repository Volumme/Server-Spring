package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.User;
import lombok.Setter;

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
