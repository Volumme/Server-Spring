package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.Routine;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineResponseDto implements IResponseDto<RoutineResponseDto, Routine>{

    private String username;

    private String routineName;

    @Override
    public RoutineResponseDto fromEntity(Routine routine) {
        return RoutineResponseDto.builder()
                .username(routine.getUser().getNickname())
                .routineName(routine.getName())
                .build()
                ;
    }
}
