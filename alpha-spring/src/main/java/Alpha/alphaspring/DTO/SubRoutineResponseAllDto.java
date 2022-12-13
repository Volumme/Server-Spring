package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.SubSet;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubRoutineResponseAllDto {

    private String workOut;
    private List<SubSetResponseDto> subSets;

    public SubRoutineResponseAllDto fromEntity(String workOut, List<SubSetResponseDto> subSets){
        return SubRoutineResponseAllDto.builder()
                .workOut(workOut)
                .subSets(subSets)
                .build()
                ;
    }
}
