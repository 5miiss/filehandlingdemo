package com.example.filehandlingdemo.user.business.servicesImpl;

import com.example.filehandlingdemo.user.persistence.entities.Role;
import com.example.filehandlingdemo.user.persistence.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Slf4j
@Service
public class JwtService {

    //    minimum requirement for jwt is 256bit
    private static final String SECRET_KEY = "77217A25432A462D4A614E645267556B58703273357638782F413F4428472B4B";
    public String extractUsername(String token) {
//        subject should be username or email of my user
        return extractClaim(token,Claims::getSubject);
    }
    public  <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public String generateToken(User userDetails){
        Map<String , Object> map = new HashMap<>();
        map.put("UUID",userDetails.getRef());

        return generateToken(map , userDetails);
    }
    public String generateRefreshToken(User userDetails){
        Map<String , Object> map = new HashMap<>();
        map.put("UUID",userDetails.getRef());
        return generateRefreshToken(map, userDetails);
    }
    public String generateToken(Map<String , Object> extraClaims , User userDetails){
        Date in = new Date();
        int months =((Role) userDetails.getRoles().toArray()[0]).getName().equals("ROLE_FAC")? 24 : 6;
        log.info("month : {}",months);
        log.info("roles : {}",userDetails.getRoles());
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault()).plusMonths(months);
        Date validTill = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("roles",userDetails.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis())) //means when this Claim was Created
                .setExpiration(validTill) //6 months
                .signWith( getSignInKey(),SignatureAlgorithm.HS256)
                .compact(); //compact will generate and return the token
    }
    public String generateRefreshToken(Map<String , Object> extraClaims , User userDetails){
        Date in = new Date();
        int years = ((Role) userDetails.getRoles().toArray()[0]).getName().equals("ROLE_FAC")? 6 : 1;
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault()).plusYears(years);
        Date validTill = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .claim("roles",userDetails.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis())) //means when this Claim was Created
                .setExpiration(validTill) //1 year
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact(); //compact will generate and return the token
    }


    //    validate that this token belong to this userDetails
    public boolean isTokenValid(String token , UserDetails userDetails){
        final  String userName = extractUsername(token);
        return  (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }

    //    Claims is an extra data send with jwt
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())//setSigningKey uses to create the signature (secret key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUUIDFromRequest(HttpServletRequest request){
        String token;
        try {
            String operator = request.getHeader("Authorization");
            token = operator.substring(7);
        }catch (Exception e){
             throw new IllegalArgumentException("Cannot find Token");
        }

        return extractAllClaims(token).get("UUID").toString();
    }
}