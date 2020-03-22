package pl.itj.dev.services.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.itj.dev.services.authservice.constants.Token;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private String secretKey;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, String secretKey) {
        super(authenticationManager);
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(Token.HEADER_STRING);

        if (header == null || !header.startsWith(Token.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Token.HEADER_STRING);
        if (token != null) {
            try{
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey)
                        .parseClaimsJws(token.replace(Token.TOKEN_PREFIX, ""));

                if(claimsJws.getBody().getExpiration().after(Date.from(Instant.now()))) {
                    List<String> authorities = claimsJws.getBody().get("roles", List.class);
                    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));

                    return new UsernamePasswordAuthenticationToken(claimsJws.getBody().getSubject(), null, grantedAuthorities);
                }
            }catch (MalformedJwtException mje) {
                log.warn("Malformed JWT");
            }
        }
        return null;
    }
}
