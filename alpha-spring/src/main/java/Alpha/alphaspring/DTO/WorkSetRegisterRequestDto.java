package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.SubRoutine;
import Alpha.alphaspring.domain.WorkOut;
import Alpha.alphaspring.domain.WorkSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkSetRegisterRequestDto implements IRequestDto<WorkSet, SubRoutine> {
    private String workOutName;
    private long subRoutineId;

    public WorkSet toEntity(SubRoutine subRoutine, WorkOut workOut) {
        return WorkSet.builder()
                .workOut(workOut)
                .subRoutine(subRoutine)
                .build();
    }
}
