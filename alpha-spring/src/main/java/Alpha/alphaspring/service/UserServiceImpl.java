package Alpha.alphaspring.service;

import Alpha.alphaspring.DTO.UserRegisterRequestDto;
import Alpha.alphaspring.Utils.CommonTokenUtils;
import Alpha.alphaspring.Utils.KakaoTokenUtils;
import Alpha.alphaspring.domain.User;
import Alpha.alphaspring.repository.UserRepository;

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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpHeaders;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
@PropertySource("classpath:application.properties")
public class UserServiceImpl {

    // use env to get kakao native key from application.properties
    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommonTokenUtils tokenUtils;
    @Autowired
    private KakaoTokenUtils kakaoTokenUtils;

    //
    public List<User> findUsers() throws Exception {
        if (userRepository.findAll().isEmpty()){
            throw new Exception("result set null");
        }
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

    public boolean checkKakaoToken(String token) throws ParseException, JwkException {
        return kakaoTokenUtils.validate(token);
    }

    public void join(Map<String, Object> headers, UserRegisterRequestDto request) throws ParseException {
        String authorizationHeader = (String) headers.get("authorization");
        String[] bearerHeader = authorizationHeader.split(" ");
        String jwtToken = bearerHeader[1];

        String subject = tokenUtils.getSubject(jwtToken);
        String provider = tokenUtils.getIssuer(jwtToken);

        Map<String, Object> args = new HashMap<>();
        args.put("provider", provider);
        args.put("username", subject);

        User user = request.toEntity(args);

        userRepository.save(user);
    }


    public User findUser(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("ohlcv result set null"));
    }
}
