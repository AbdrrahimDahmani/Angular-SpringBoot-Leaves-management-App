package com.example.Gestion.Security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Gestion.Models.User;
import com.example.Gestion.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final UserRepository userRepository;

    @Value("${secret.key}")
    private String secretkey;
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(secretkey.getBytes());
                JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();

                String email = decodedJWT.getSubject(); // Récupérer l'e-mail depuis les revendications
                System.out.println("hi "+email);
                Optional<User> optionalUser = (userRepository.findByEmail(email));

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();

                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    Arrays.stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    ErroResponse errroResponse = new ErroResponse(FORBIDDEN, "Utilisateur non trouvé");
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.setStatus(errroResponse.getStatusCodeValue());
                    new ObjectMapper().writeValue(response.getOutputStream(), errroResponse);
                }
            } catch (Exception e) {
                ErroResponse errroResponse = new ErroResponse(FORBIDDEN, e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(errroResponse.getStatusCodeValue());
                new ObjectMapper().writeValue(response.getOutputStream(), errroResponse);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
