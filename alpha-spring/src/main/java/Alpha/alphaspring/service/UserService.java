package Alpha.alphaspring.service;

import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class UserService {

    private final String kakaoKey = "qGWf6RVzV2pM8YqJ6by5exoixIlTvdXDfYj2v7E6xkoYmesAjp_1IYL7rzhpUYqIkWX0P4wOwAsg-Ud8PcMHggfwUNPOcqgSk1hAIHr63zSlG8xatQb17q9LrWny2HWkUVEU30PxxHsLcuzmfhbRx8kOrNfJEirIuqSyWF_OBHeEgBgYjydd_c8vPo7IiH-pijZn4ZouPsEg7wtdIX3-0ZcXXDbFkaDaqClfqmVCLNBhg3DKYDQOoyWXrpFKUXUFuk2FTCqWaQJ0GniO4p_ppkYIf4zhlwUYfXZEhm8cBo6H2EgukntDbTgnoha8kNunTPekxWTDhE5wGAt6YpT4Yw";

    private HashMap<String, String> sessions = new HashMap<>();
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public void store_token(String tokens) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject tokenObj = (JSONObject) parser.parse(tokens);
        String idToken = (String) tokenObj.get("IdToken");

        String[] jwt = idToken.split("\\.");

        byte[] decodedBytes = Base64.getDecoder().decode(jwt[1]);
        String payload = new String(decodedBytes);

//        sessions.put("", idToken);
        System.out.println("payload = " + payload);
    }

    public boolean check_token(String token) throws ParseException {
        // json token parsing
        JSONParser parser = new JSONParser();
        JSONObject tokenObj = (JSONObject) parser.parse(token);

        // extract idtoken
        String idToken = (String) tokenObj.get("IdToken");

        //seperate token
        String[] jwt = idToken.split("\\.");

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
        if (!"e5f9a7629df9bf988dac8d499f8559ea".equals(aud)) {
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

        //decode header
        decodedBytes = Base64.getDecoder().decode(jwt[0]);
        String header = new String(decodedBytes);

        //extract private key

        //validate digital signature
        RSAPublicKey publicKey = kakaoKey;
        RSAPrivateKey privateKey =
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exce
    }

}
