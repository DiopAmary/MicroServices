package ma.enset.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token= request.getHeader(SecurityConstants.HEADER_STRING);
        if(token==null || request.getServletPath().equals("/refreshToken")){
            filterChain.doFilter(request,response);
        }else{
            if (token != null && token.startsWith("Bearer")){
                try{
                    String jwt= token.substring(7);
                    Algorithm algorithm= Algorithm.HMAC256(SecurityConstants.SECRET);
                    JWTVerifier jwtVerifier= JWT
                            .require(algorithm)
                            .build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username= decodedJWT.getSubject();
                    System.out.println(username);
                    String[] roles= decodedJWT.getClaim("roles").asArray(String.class);
                    System.out.println(roles);
                    Collection<GrantedAuthority> authorities= new ArrayList<>();
                    for(String role: roles){
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }catch (TokenExpiredException e){
                    response.setHeader("Error-Message",e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
