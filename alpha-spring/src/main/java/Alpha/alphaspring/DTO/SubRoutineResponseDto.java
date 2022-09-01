package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.Routine;
import Alpha.alphaspring.domain.SubRoutine;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubRoutineResponseDto implements IResponseDto<SubRoutineResponseDto, SubRoutine> {

    private String routineName;

    private Long subRoutineId;

    private String subRoutineName;

    @Override
    public SubRoutineResponseDto fromEntity(SubRoutine subRoutine) {
        return SubRoutineResponseDto.builder()
                .subRoutineId(subRoutine.getId())
                .routineName(subRoutine.getRoutine().getName())
                .subRoutineName(subRoutine.getName())
                .build()
                ;
    }
}
