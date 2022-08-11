package Alpha.alphaspring.Utils;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthInfo {
    String provider;
    String userId;
}
