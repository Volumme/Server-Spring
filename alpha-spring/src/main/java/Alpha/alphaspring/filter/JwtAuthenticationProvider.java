package Alpha.alphaspring.filter;

import Alpha.alphaspring.DTO.UserDetails;
import Alpha.alphaspring.Utils.KakaoTokenUtils;
import Alpha.alphaspring.Utils.OauthInfo;
import Alpha.alphaspring.Utils.TokenUtils;
import Alpha.alphaspring.service.UserDetailsServiceImpl;
import Alpha.alphaspring.service.UserServiceImpl;
import com.auth0.jwk.JwkException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    KakaoTokenUtils tokenUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken beforeToken = (JwtAuthenticationToken) authentication;
        String jwtToken = beforeToken.getCredential();

        try {
            if(!tokenUtils.validate(jwtToken)){
                throw new BadCredentialsException("token not valid");
            }
            OauthInfo oauthInfo = tokenUtils.getOauthInfo(jwtToken);
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
