package Alpha.alphaspring.Utils;

import com.auth0.jwk.JwkException;
import org.json.simple.parser.ParseException;

public interface TokenUtils {
    boolean validate(String token) throws ParseException, JwkException;
    OauthInfo getOauthInfo(String token) throws ParseException;
}
