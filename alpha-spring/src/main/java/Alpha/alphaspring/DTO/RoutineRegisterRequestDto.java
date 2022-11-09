package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoutineRegisterRequestDto implements IRequestDto<Routine, User>{
    private String name;
    private boolean isRecommended;
    private String description;
    private String category;
    @Override
    public Routine toEntity(User user) {
        return Routine.builder()
                .user(user)
                .name(name)
                .description(description)
                .isRecommended(isRecommended)
                .category(category)
                .build();
    }
}
