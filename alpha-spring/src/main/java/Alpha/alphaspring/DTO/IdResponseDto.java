package Alpha.alphaspring.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdResponseDto {
    private final long id;
    public IdResponseDto(long id) {
        this.id = id;
    }
}
