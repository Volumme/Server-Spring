package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.SubSet;
import Alpha.alphaspring.domain.WorkSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubSetRegisterRequestDto implements IRequestDto<SubSet, WorkSet>{

    private Long workSetId;

    private Long setNo;

    private Long count;

    private Long weight;

    @Override
    public SubSet toEntity(WorkSet workSet){
        return SubSet.builder()
                .count(count)
                .workSet(workSet)
                .weight(weight)
                .setNo(setNo)
                .build();
    }
}
