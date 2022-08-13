package Alpha.alphaspring.Utils;

import com.auth0.jwk.JwkException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Base64;

public abstract class AbstractTokenUtils implements TokenUtils {

    public String getHeader(String token) {
        String JWT_SEPARATOR = "\\.";
        JSONParser parser = new JSONParser();

        String[] jwt = token.split(JWT_SEPARATOR);
        return jwt[0];
    }

    public String getPayload(String token) {
        String JWT_SEPARATOR = "\\.";
        JSONParser parser = new JSONParser();

        String[] jwt = token.split(JWT_SEPARATOR);
        return jwt[1];
    }

    public String getJsonHeader(String token) {
        String header = getHeader(token);

        byte[] decodedBytes = Base64.getDecoder().decode(header);
        return new String(decodedBytes);
    }

    public String getJsonPayload(String token) {
        String payload = getPayload(token);

        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        return new String(decodedBytes);
    }
}
