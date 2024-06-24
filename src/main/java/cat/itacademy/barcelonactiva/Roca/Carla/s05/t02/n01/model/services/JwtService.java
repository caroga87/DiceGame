package cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.Roca.Carla.s05.t02.n01.utils.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secret_key;

    public String generateToken (UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken (Map <String, Objects> extractClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public String getUsername (String token) {
        return getClaim (token, Claims :: getSubject);
    }

    public <T> T getClaim (String token, Function <Claims, T> claimsResolver) {
        final Claims claims = getAllClaims (token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaims (String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey () {
        byte [] keyBytes  = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired (token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before (new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}

