package Alpha.alphaspring.Utils;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Component
public class KakaoTokenUtils implements TokenUtils{

    @Autowired
    Environment env;

    private final JwkProvider kakaoProvider;


    public KakaoTokenUtils() {
        this.kakaoProvider = new JwkProviderBuilder("https://kauth.kakao.com")
                .cached(10, 7, TimeUnit.DAYS) // 7일간 최대 10개 캐시
                .build();
    }


    @Override
    public boolean validate(String token) throws ParseException, JwkException {
        // json token parsing
        JSONParser parser = new JSONParser();
//        JSONObject tokenObj = (JSONObject) parser.parse(token);

        // extract idtoken
//        String idToken = (String) tokenObj.get("IdToken");

        //seperate token
        String[] jwt = token.split("\\.");

        //decode payload -> json
        byte[] decodedBytes = Base64.getDecoder().decode(jwt[1]);
        String payload = new String(decodedBytes);

        //extract iss
        JSONObject payloadObj = (JSONObject) parser.parse(payload);
        String iss = (String) payloadObj.get("iss");

        //check iss
        if (!"https://kauth.kakao.com".equals(iss)) {
            System.out.println("iss = " + iss);
            return false;
        }

        //extract aud
        String aud = (String) payloadObj.get("aud");

        //check aud
        if (!env.getProperty("spring.kakao.nativeappkey").equals(aud)) {
            System.out.println("env.getProperty(\"spring.kakao.nativeappkey\") = " + env.getProperty("spring.kakao.nativeappkey"));
            System.out.println("aud = " + aud);
            return false;
        }
        //extract exp
        long exp = (long) payloadObj.get("exp");

        //check exp
        long curTime = System.currentTimeMillis()/1000;
        if(curTime >= exp){
            System.out.println("curTime = " + curTime);
            System.out.println("exp = " + exp);
            return false;
        }

// 1. 검증없이 디코딩
        DecodedJWT jwtOrigin = JWT.decode(token);
//        System.out.println("jwtOrigin = " + jwtOrigin.getToken());

// 2. 공개키 프로바이더 준비
        Jwk jwk = kakaoProvider.get(jwtOrigin.getKeyId());

// 3. 검증 및 디코딩
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decodedJwt = verifier.verify(token);
//        System.out.println("decodedJwt = " + decodedJwt.getToken());
        return true;
    }



    @Override
    public OauthInfo getOauthInfo(String token) throws ParseException {
        JSONParser parser = new JSONParser();
        DecodedJWT jwtOrigin = JWT.decode(token);
        String payloadStr = jwtOrigin.getPayload();
        byte[] decodedBytes = Base64.getDecoder().decode(payloadStr);
        String payload = new String(decodedBytes);
        JSONObject payloadObj = (JSONObject) parser.parse(payload);
        String id = (String) payloadObj.get("sub");
        return OauthInfo.builder().userId(id).provider("kakao").build();
    }
}
