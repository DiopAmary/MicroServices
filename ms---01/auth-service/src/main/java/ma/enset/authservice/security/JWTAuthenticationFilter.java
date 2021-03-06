package ma.enset.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ma.enset.authservice.entities.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(request.getParameter("username"));
        userEntity.setPassword(request.getParameter("password"));
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(),userEntity.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User authenticatedUser= (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);

        String jwtAccessToken = JWT
                .create()
                .withSubject(authenticatedUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",authenticatedUser.getAuthorities().stream().map((a)->a.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);

        String jwtRefreshToken = JWT
                .create()
                .withSubject(authenticatedUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String,String> accessToken = new HashMap<>();
        accessToken.put("Access_Token",jwtAccessToken);
        accessToken.put("Refresh_Token",jwtRefreshToken);
        response.setContentType("application/json");
        new JsonMapper().writeValue(response.getOutputStream(),accessToken);
    }
}
