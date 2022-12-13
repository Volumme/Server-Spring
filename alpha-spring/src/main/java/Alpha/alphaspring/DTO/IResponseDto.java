package Alpha.alphaspring.DTO;

import Alpha.alphaspring.domain.SubSet;
import Alpha.alphaspring.domain.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IResponseDto<R, T> {
     default R fromEntity() {
          return null;
     }
     default  R fromEntity(T args){
          return null;
     }

}
