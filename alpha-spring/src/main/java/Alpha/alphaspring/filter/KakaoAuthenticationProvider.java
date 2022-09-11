package Alpha.alphaspring.filter;

import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.Utils.KakaoTokenUtils;
import Alpha.alphaspring.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class KakaoAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    KakaoTokenUtils kakaoTokenUtils;
    private final String provider = "kakao";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(!provider.equals(authentication.getPrincipal())) return null;

        String token = (String) authentication.getCredentials();
        try {
            if (!kakaoTokenUtils.validate(token)) return null;
            String id = kakaoTokenUtils.getOauthInfo(token).getUserId();

            UserDetails userDetails = UserDetails.builder()
                    .username(id)
                    .provider(provider)
                    .isLocked(false)
                    .isExpired(false)
                    .isEnabled(true)
                    .build();

            return new SnsAuthenticationToken(userDetails, userDetails.getAuthorities());

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SnsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
