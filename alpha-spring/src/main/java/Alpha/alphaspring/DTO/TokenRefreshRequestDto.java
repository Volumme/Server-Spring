package Alpha.alphaspring.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequestDto implements IRequestDto<Object, Object>{
    private String refreshToken;

}
