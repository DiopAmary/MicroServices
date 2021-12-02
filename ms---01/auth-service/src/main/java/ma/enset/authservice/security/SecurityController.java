package ma.enset.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import ma.enset.authservice.entities.UserEntity;
import ma.enset.authservice.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SecurityController {
    @Autowired
    Services services;

    @GetMapping(path = "/refreshToken")
    public ResponseEntity<Map<String, String>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token= request.getHeader("Authorization");
        if (token!= null&& token.startsWith("Bearer")){
            try {
                String jwtRefreshToken= token.substring(7);
                Algorithm algorithm= Algorithm.HMAC256(SecurityConstants.SECRET);
                JWTVerifier jwtVerifier= JWT
                        .require(algorithm)
                        .build();
                DecodedJWT decodedJWT= jwtVerifier.verify(jwtRefreshToken);
                String username = decodedJWT.getSubject();
                UserEntity userEntity = services.findUser(username);
                String jwtAccessToken= JWT
                        .create()
                        .withSubject(userEntity.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userEntity.getRoles()
                                .stream()
                                .map(e -> e.getRole())
                                .collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> accessToken= new HashMap<>();
                accessToken.put("Access_Token", jwtAccessToken);
                accessToken.put("Refresh_Token", jwtRefreshToken);
                response.setContentType("application/json");
                return new ResponseEntity<>(accessToken, HttpStatus.OK);
            } catch(TokenExpiredException e){
                response.setHeader("Error-Message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        throw new RuntimeException("Bad RefreshToken");
    }
}
