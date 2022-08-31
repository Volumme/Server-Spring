package Alpha.alphaspring.filter;

import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.Utils.*;
import Alpha.alphaspring.service.UserDetailsService;
import com.auth0.jwk.JwkException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    KakaoTokenUtils kakaoTokenUtils;
    @Autowired
    GoogleTokenUtils googleTokenUtils;
//    @Autowired
//    NaverTokenUtils naverTokenUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken beforeToken = (JwtAuthenticationToken) authentication;
        String jwtToken = beforeToken.getCredential();

        try {
            if (!jwtTokenUtils.validate(jwtToken)){
                throw  new BadCredentialsException("token not valid");
            }
            OauthInfo oauthInfo = jwtTokenUtils.getOauthInfo(jwtToken);
            String userId = oauthInfo.getUserId();
            String provider = oauthInfo.getProvider();
            UserDetails user = userDetailsService.findUser(userId, provider);
            return new JwtAuthenticationToken(user, user.getAuthorities());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}



