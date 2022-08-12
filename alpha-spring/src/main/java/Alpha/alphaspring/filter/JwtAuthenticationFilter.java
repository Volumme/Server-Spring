package Alpha.alphaspring.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    final String AUTH_HEADER_NAME = "Authorization";
    final String BEARER_NAME = "Bearer";

    protected JwtAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/**", authenticationManager);
    }
    protected JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    protected JwtAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    protected JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String authHeader = request.getHeader(AUTH_HEADER_NAME);
        String[] authHeaderValue = authHeader.split(" ");
        if (authHeaderValue.length != 2){
            throw new RuntimeException("invalid auth header, require token");
        }
        if(!BEARER_NAME.equals(authHeaderValue[0])) {
            throw new RuntimeException("invalid auth header, require bearer");
        }
        String jwtToken = authHeaderValue[1];

        Authentication authToken = new JwtAuthenticationToken(jwtToken);

        return this.getAuthenticationManager().authenticate(authToken);
    }
}
