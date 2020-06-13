package com.example1PhotoApp.api.users.usersService.security;

import com.example1PhotoApp.api.users.usersService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    public static final String USERS_LOGIN_PATH = "/users/login";

    //part 1, showing how to enable spring security for the app.
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/api/users/**").permitAll();
//    }

    //part 2, enabling only specific IP addresses to be able to access any path within the application

    @Value("${gateway.ip}")
    private String gatewayIpAddress;
    @Value("${jwt.token.expiration}")
    private String jwtExpirationDuration;
    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService usersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/**").hasIpAddress(gatewayIpAddress)
        .and()
        .addFilter(getAuthenticationFilter());
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(
                usersService,
                authenticationManager(),
                jwtExpirationDuration,
                jwtSecret
        );
        authenticationFilter.setFilterProcessesUrl(USERS_LOGIN_PATH);
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }
}
