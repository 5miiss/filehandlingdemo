package com.example.filehandlingdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] WHITELIST = {
//            "/api/v1/user/login",
//            "/api/v1/user/register",

            "/api/v1/QR/**",
            "/api/v1/user/**",
            "/uploads/**",
            "/api/v1/driver/**",
            "/api/v1/client/**",
            "/api/v1/order/**",
            "/api/v1/address/**",
            "/api/v1/carModel/**",
            "/api/v1/carMake/**",
            "/api/v1/banks/**",
            "/api/v1/country/**",

            "/api/v1/Auth/**",
            "/api/v1/appSettings/**",
            "/api/v1/role/**",
            "/api/v1/apikey/**",
            "/v3/api-docs/",
            "/v3/api-docs/**",
            "/swagger-resources**",
            "/swagger-ui**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .cors().and() //disable some kind of verification
                .authorizeHttpRequests()
                .shouldFilterAllDispatcherTypes(false)
                .antMatchers(WHITELIST) //my white list any one can access
                .permitAll()
                .antMatchers("/user/auth")
                .hasRole("DRIVER")
                .anyRequest() //any other requests must be auth
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //let spring create new session for every request
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //add jwt filter before username pass auth filter



        return http.build();
    }

}