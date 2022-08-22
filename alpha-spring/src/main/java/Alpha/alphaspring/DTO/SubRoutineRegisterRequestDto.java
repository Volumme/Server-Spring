package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.SubRoutine;
import Alpha.alphaspring.domain.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubRoutineRegisterRequestDto implements IRequestDto<SubRoutine, Routine>{

    private String subRoutineName;
    private String routineName;

    @Override
    public SubRoutine toEntity(Routine routine) {
        return SubRoutine.builder()
                .routine(routine)
                .name(subRoutineName)
                .build();
    }
}
