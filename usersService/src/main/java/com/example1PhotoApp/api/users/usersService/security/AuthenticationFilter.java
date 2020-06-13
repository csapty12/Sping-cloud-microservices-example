package com.example1PhotoApp.api.users.usersService.security;

import com.example1PhotoApp.api.users.usersService.model.LoginCommand;
import com.example1PhotoApp.api.users.usersService.model.UserResponse;
import com.example1PhotoApp.api.users.usersService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private String jwtExpirationDuration;
    private String jwtSecret;

    public AuthenticationFilter(UserService userService,
                                AuthenticationManager authenticationManager,
                                String jwtExpirationDuration,
                                String jwtSecret) {
        this.userService = userService;
        this.jwtSecret = jwtSecret;
        this.jwtExpirationDuration = jwtExpirationDuration;
        super.setAuthenticationManager(authenticationManager);
    }

    /*
        this method will be called by spring when the request comes in.
        as we have access to the request, we can get the email and password from that.
        we give this to a UsernamePasswordAuthenticationToken which is then used by the authentication manager to authenticate the user
         */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginCommand loginCommand = new ObjectMapper().readValue(request.getInputStream(), LoginCommand.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginCommand.getEmail(), loginCommand.getPassword(), new ArrayList<>()
                    )
            );

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /*
    if authentication is successful, then we override this method
     */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("you have been authenticated!!! :D");

        //we want to take the user details, taking the username and generating a JWT with that, so that for any
        //other request that they are sending, we know that this user is successfully authenticated
        // we can send back the JWT in the response header
        String userEmail = authResult.getName();
        UserResponse userDetails = userService.getUserByEmail(userEmail);
        String token = Jwts.builder()
                .setSubject(userDetails.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpirationDuration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getId().toString());

    }
}
