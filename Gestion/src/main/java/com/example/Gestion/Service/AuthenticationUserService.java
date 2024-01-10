package com.example.Gestion.Service;

import com.example.Gestion.Models.Role;
import com.example.Gestion.Models.User;
import com.example.Gestion.Repository.UserRepository;
import com.example.Gestion.Security.JwtService;
import com.example.Gestion.auth.AuthenticationRequest;
import com.example.Gestion.auth.AuthenticationResponse;
import com.example.Gestion.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationUserService  {
    @Autowired
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;



    public ResponseEntity<?> register(RegisterRequest registerRequest){
        try {
            if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
                throw new IllegalArgumentException("user with this email : "+ registerRequest.getEmail()+" exist");
            }
            userService.createUser(new User(registerRequest.getFull_name(),registerRequest.getEmail(),registerRequest.getPassword(),registerRequest.getRole()));
            User user=userRepository.findByEmail(registerRequest.getEmail()).orElseThrow();
            return  ResponseEntity.ok(user);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

    }
    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest){
        try {

            User user=userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(()-> new NoSuchElementException("user not found"));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Set<Role> roles=new HashSet<>();
            roles.stream().forEach(c->{roles.add(new Role(c.getName()));
                authorities.add(new SimpleGrantedAuthority(c.getName()));
            });
            user.setRoles(roles);
            roles.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
            var jwtAccessToken=jwtService.generateToken(user,authorities);
            var jwtRefreshToken=jwtService.generateRefreshToken(user,authorities);
            return ResponseEntity.ok(AuthenticationResponse.builder().id(user.getId()).access_token(jwtAccessToken).refresh_token(jwtRefreshToken).email(user.getEmail()).role(user.getRole()).build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("invalid credential : "+ e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());


        }
    }

}
