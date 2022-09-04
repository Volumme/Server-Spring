package Alpha.alphaspring.Utils;

import com.auth0.jwk.JwkException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;

public interface TokenUtils {
    String getHeader(String token);
    String getPayload(String token);
    String getJsonHeader(String token);
    String getJsonPayload(String token);
    String getClaim(String token, String key, String scope) throws ParseException;
    boolean validate(String token) throws ParseException, JwkException;
    OauthInfo getOauthInfo(String token) throws ParseException;
}
