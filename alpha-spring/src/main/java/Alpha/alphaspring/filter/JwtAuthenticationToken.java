package Alpha.alphaspring.filter;

import Alpha.alphaspring.DTO.UserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private final UserDetails user;

    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.user = null;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetails user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = null;
        this.user = user;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

}
