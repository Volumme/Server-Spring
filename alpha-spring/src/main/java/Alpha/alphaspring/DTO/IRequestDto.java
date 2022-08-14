package Alpha.alphaspring.DTO;

import java.util.Map;

public interface IRequestDto {
    Object toEntity(Map<String, Object> args);
}
