package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IResponseDto<R, T> {
     R fromEntity(T args);

}
