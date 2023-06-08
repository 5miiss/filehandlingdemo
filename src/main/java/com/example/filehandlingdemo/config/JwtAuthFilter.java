package com.example.filehandlingdemo.config;



import com.example.filehandlingdemo.apikey.persistence.Repository.ApiKeyRepo;
import com.example.filehandlingdemo.user.business.servicesImpl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ApiKeyRepo apiKeyRepo;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
//        get the Jwt(Bearer) token  from header called Authorization -> should start with "Bearer "
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        //  add apikey with header X-API-Key
        final String apiKey = request.getHeader("X-API-Key");
        logger.info("url : "+request.getRequestURI());
        //for swagger
//        List<String> uris = new ArrayList<>(List.of("/swagger","/favicon","/v3/api-"));
//        logger.info(uris.contains(request.getRequestURI().substring(0,8)));
        if(!Pattern.compile(".*/swagger.*|.*/favicon.*|.*/v3/api-.*|.*/uploads.*").matcher(request.getRequestURI()).matches()){
            logger.info("is in");
            if (apiKey == null || !apiKeyRepo.existsByApiKeyAndIsExpiredFalse(apiKey)){
                response.setStatus(403);
                String newContent = "{\"message\":\"you Must provide valid ApiKey in the header \""+
                        ",\"status\":false"+",\"code\":403}";
                response.setContentLength(newContent .length());
                response.setContentType("application/json");
                response.getWriter().write(newContent);
                return;
            }
        }
//        if(Pattern.compile(".*/login.*").matcher(request.getRequestURI()).matches()){
//
//        }
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
//          go to next filter and pass request and response to the next one
            logger.info("iam in auth header check");
            filterChain.doFilter(request,response);
            return;
        }
//       extract jwt token "Bearer "-> 7 chars
        jwt = authHeader.substring(7);
//        extract username from JWT token
        username = jwtService.extractUsername(jwt);
//        check if the user not auth yet
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt , userDetails)){

//                needed by security context holder to update security context
                UsernamePasswordAuthenticationToken authToken = new  UsernamePasswordAuthenticationToken(
                        userDetails ,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}