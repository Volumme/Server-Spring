package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.WorkOut;
import lombok.Setter;

@Setter
public class WorkOutRegisterRequestDto implements IRequestDto<WorkOut, Object> {
    private String bodyPart;

    private String machineName;

    private String name;

    @Override
    public WorkOut toEntity(){
        return WorkOut.builder()
                .bodyPart(bodyPart)
                .machineName(machineName)
                .name(name)
                .build();
    }
}
