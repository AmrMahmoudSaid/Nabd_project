package com.example.nabd.security;

import com.example.nabd.exception.NabdAPIExeption;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
//    @Value("$JWT_SECRET_KEY")
    private String jwtSecret =  "931949128c37ce0092f6eb8fc450f6e844f19103c6c626683437e76cef3bd855";
    private int jwtExpiratioData = 86400000;
    public String generateToken(String email){
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+jwtExpiratioData);
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }
    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getEmail(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        return email;
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (SignatureException | MalformedJwtException exception) {
            throw new NabdAPIExeption("Invalid JWT token.", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException exception) {
            throw new NabdAPIExeption("Expired JWT token.", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException exception) {
            throw new NabdAPIExeption("Unsupported JWT token.", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException exception) {
            throw new NabdAPIExeption("JWT claims string is empty.", HttpStatus.BAD_REQUEST);
        }
    }


}
