package Alpha.alphaspring.DTO;

import java.util.Map;

public interface IRequestDto<T> {
    T toEntity(Map<String, Object> args);
}
