package Alpha.alphaspring.filter;

import Alpha.alphaspring.DTO.UserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final String credential;

    public JwtAuthenticationToken(String token) {
        super(null);
        this.principal = token; // TODO: JWTParser로 사용자 ID 같은 것만 빼내든 뭐든 하는게 어떨지..?
        this.credential = token;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = userDetails;
        this.credential = null;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credential;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
